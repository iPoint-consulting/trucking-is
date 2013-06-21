package com.ipoint.cargo4me.client.application.loadunload;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Container;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.FileUpload;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.ipoint.cargo4me.client.jsonvalidator.JSONValidatorFactory;
import com.ipoint.cargo4me.client.jsonvalidator.JSONValidatorFactory.JSONValidationException;
import com.ipoint.cargo4me.client.util.LoadUserDataCallBackHandler;
import com.ipoint.cargo4me.shared.dto.CargoOwnerDTO;
import com.ipoint.cargo4me.shared.dto.CarrierDTO;
import com.ipoint.cargo4me.shared.dto.UserDTO;

public class LoadUnloadView extends ViewWithUiHandlers<LoadUnloadUiHandlers>
		implements LoadUnloadPresenter.MyView, LoadUserDataCallBackHandler {

	private String typeOfLoading;

	private boolean fileWithCorrectExtensionSelected = true;

	private String validExtension = "jso";

	private static final String SERVER_ADDRESS = "http://test-trucking.appspot.com";

	public interface Binder extends UiBinder<Widget, LoadUnloadView> {
	}

	@UiField
	Button buttonLoadCarriers;

	@UiField
	Button buttonLoadSenders;

	@UiField
	Button buttonImport;

	@UiField
	Container contentSlot;

	@UiField
	ControlGroup fileLoadGroup;

	@UiField
	Label loadTypeValue;

	@UiField
	FormPanel uploadForm;

	@UiField
	FileUpload fileUpload;

	JSONValidatorFactory jsonValidatorFactory = new JSONValidatorFactory();

	@Inject
	public LoadUnloadView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		setupUpload();
		fileUpload.setId("files");
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (content != null) {
			contentSlot.clear();
			contentSlot.add(content);
		}
	}

	@UiHandler("buttonLoadCarriers")
	public void onLoadCarriersClick(ClickEvent clickEvent) {
		loadTypeValue.setText("Выберите файл с данными о перевозчиках");
		fileLoadGroup.setVisible(true);
		typeOfLoading = "carriers";
	}

	@UiHandler("buttonLoadSenders")
	public void onLoadSendersClick(ClickEvent clickEvent) {
		loadTypeValue.setText("Выберите файл с данными об отправителях");
		fileLoadGroup.setVisible(true);
		typeOfLoading = "senders";
	}

	@UiHandler("buttonImport")
	public void onInportButtonClick(ClickEvent clickEvent) {
		getUiHandlers().authorize();
		// startImport();
	}

	@Override
	public void startImport() {
		getUiHandlers().cancelImport(false);
		loadTypeValue.setText("Импорт данных на сайт");
		fileLoadGroup.setVisible(true);

		if (buttonImport.isEnabled()) {
			if (fileUpload.getFilename().length() > 0) {
				if (fileWithCorrectExtensionSelected) {
					fileLoadGroup.setVisible(false);
					List<UserDTO> users = getUiHandlers().getSelectedUsers();
					if (users.size() > 0) {
						buttonImport.setEnabled(false);
						getUiHandlers().showProgressDialog();
						getUiHandlers().resetCurrentUser();
						getUiHandlers().sendUsersCount(users.size());
						getUiHandlers().setListUsers(users);
						if (typeOfLoading.equals("carriers")) {
							getUiHandlers().setRequestUrl(
									SERVER_ADDRESS + "/api/v1/drivers/insert");
							getUiHandlers().sendToTruckingIntegrationServer();
						} else if (typeOfLoading.equals("senders")) {
							getUiHandlers().setRequestUrl(
									SERVER_ADDRESS
											+ "/api/v1/cargo_owners/insert");
							getUiHandlers().sendToTruckingIntegrationServer();
						}
					} else {
						Window.alert("Не выбраны пользователи для загрузки.");
					}
				} else {
					buttonImport.setEnabled(true);
					Window.alert("Не правильное расширение файла. Только "
							+ validExtension + " файлы разрешены.");
				}
			} else {
				Window.alert("Пожалуйста, выберите файл для загрузки.");
			}
		}
	}

	private void setupUpload() {
		uploadForm.addSubmitCompleteHandler(new SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				uploadForm.reset();
			}

		});
		fileUpload.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				fileWithCorrectExtensionSelected = true;
				String fileName = fileUpload.getFilename();
				if (fileName.length() > 0) {
					String extension = fileName.substring(
							fileName.length() - 3, fileName.length());
					callThisContext();
					if (!extension.equals(validExtension)) {
						fileWithCorrectExtensionSelected = false;
					}
				}
			}
		});
		buttonImport.setEnabled(false);
	}

	private void callThisContext() {
		UploadPanel.panel(this);
	}

	@Override
	public void setUsersJSON(String value) {
		String value1 = null;
		byte ptext[];
		try {
			ptext = value.getBytes("ISO-8859-1");
			value1 = new String(ptext, "UTF-8");
		} catch (UnsupportedEncodingException e1) {

		}
		String[] jsonArray = value1.split("\r\n");
		List<String> incorrectRecords = new ArrayList<String>();
		List<UserDTO> validRecords = new ArrayList<UserDTO>();
		int incorrectJSONCount = 0;
		JSONValue jsonValue;

		for (int i = 0; i < jsonArray.length; i++) {
			try {
				jsonValue = JSONParser
						.parseStrict(getJSONWithoutIllegalCharacters(jsonArray[i]));
				JSONObject jsonObject = jsonValue.isObject();

				UserDTO dto;
				if (typeOfLoading.equals("carriers")) {
					dto = validateAndFillCarrierFields(jsonObject);
				} else {
					dto = validateAndFillCargoOwnerFields(jsonObject);
				}

				validateAndFillCommonFields(dto, jsonObject);
				validRecords.add(dto);
			} catch (Exception e) {
				incorrectRecords.add(jsonArray[incorrectJSONCount]);
				incorrectJSONCount++;
				Window.alert(e.getMessage());
			}
		}
		getUiHandlers().showUsersTable(validRecords);
		if (validRecords.size() > 0) {
			buttonImport.setEnabled(true);
		} else {
			buttonImport.setEnabled(false);
		}
	}

	/**
	 * UserDTO fields processing
	 * 
	 * @param dto
	 * @param jsonObject
	 * @throws JSONValidationException
	 */
	private void validateAndFillCommonFields(UserDTO dto, JSONObject jsonObject)
			throws JSONValidationException {
		String stringValue = "";

		stringValue = jsonObject.get("contactPhone").isString().stringValue();
		jsonValidatorFactory.createValidator("phone").validate(stringValue);
		dto.setContactPhone(stringValue);

		stringValue = jsonObject.get("userEmail").isString().stringValue();
		jsonValidatorFactory.createValidator("email").validate(stringValue);
		dto.setUserEmail(stringValue);

		stringValue = jsonObject.get("fullName").isString().stringValue();
		jsonValidatorFactory.createValidator("name").validate(stringValue);
		dto.setFullName(stringValue);

		stringValue = jsonObject.get("type").isString().stringValue();
		jsonValidatorFactory.createValidator("type").validate(stringValue);
		dto.setType(stringValue);

		stringValue = jsonObject.get("cost").isString().stringValue();
		jsonValidatorFactory.createValidator("cost").validate(stringValue);
		dto.setCost(stringValue);

		stringValue = jsonObject.get("comment").isString().stringValue();
		dto.setComment(stringValue);

		stringValue = jsonObject.get("truckCategory").isString().stringValue();
		jsonValidatorFactory.createValidator("truckCategory").validate(
				stringValue);
		dto.setTruckCategory(stringValue);

	}

	private CarrierDTO validateAndFillCarrierFields(JSONObject jsonObject)
			throws JSONValidationException {
		CarrierDTO dto = new CarrierDTO();
		String stringValue = "";

		stringValue = jsonObject.get("name").isString().stringValue();
		jsonValidatorFactory.createValidator("trucName").validate(stringValue);
		dto.setName(stringValue);

		stringValue = jsonObject.get("cityOfRegistry").isString().stringValue();
		jsonValidatorFactory.createValidator("cityOfRegistry").validate(
				stringValue);
		dto.setCityOfRegistry(stringValue);

		stringValue = jsonObject.get("averageRatingPerKm").isString()
				.stringValue();
		jsonValidatorFactory.createValidator("averageRatingPerKm").validate(
				stringValue);
		dto.setAverageRatingPerKm(stringValue);

		stringValue = jsonObject.get("averageRatingPerHour").isString()
				.stringValue();
		jsonValidatorFactory.createValidator("averageRatingPerHour").validate(
				stringValue);
		dto.setAverageRatingPerHour(stringValue);

		stringValue = jsonObject.get("carOwner").isString().stringValue();
		jsonValidatorFactory.createValidator("carOwner").validate(stringValue);
		dto.setCarOwner(stringValue);

		stringValue = jsonObject.get("driver").isString().stringValue();
		jsonValidatorFactory.createValidator("driver").validate(stringValue);
		dto.setDriver(stringValue);

		stringValue = jsonObject.get("carRegistrationData").isString()
				.stringValue();
		jsonValidatorFactory.createValidator("carRegistrationData").validate(
				stringValue);
		dto.setCarRegistrationData(stringValue);

		stringValue = jsonObject.get("cargoType").isString().stringValue();
		jsonValidatorFactory.createValidator("cargoType").validate(stringValue);
		dto.setCargoType(stringValue);

		stringValue = jsonObject.get("typeOfShipping").isString().stringValue();
		jsonValidatorFactory.createValidator("typeOfShipping").validate(
				stringValue);
		dto.setTypeOfShipping(stringValue);

		stringValue = jsonObject.get("typeOfCar").isString().stringValue();
		jsonValidatorFactory.createValidator("typeOfCar").validate(stringValue);
		dto.setTypeOfCar(stringValue);

		return dto;
	}

	private CargoOwnerDTO validateAndFillCargoOwnerFields(JSONObject jsonObject)
			throws JSONValidationException {
		CargoOwnerDTO dto = new CargoOwnerDTO();
		String stringValue = "";

		stringValue = jsonObject.get("volume").isString().stringValue();
		jsonValidatorFactory.createValidator("volume").validate(stringValue);
		dto.setVolume(stringValue);

		stringValue = jsonObject.get("weight").isString().stringValue();
		jsonValidatorFactory.createValidator("weight").validate(stringValue);
		dto.setWeight(stringValue);

		stringValue = jsonObject.get("dispatchOther").isString().stringValue();
		jsonValidatorFactory.createValidator("dispatchOther").validate(
				stringValue);
		dto.setDispatchOther(stringValue);

		stringValue = jsonObject.get("deliveryOther").isString().stringValue();
		jsonValidatorFactory.createValidator("deliveryOther").validate(
				stringValue);
		dto.setDeliveryOther(stringValue);

		stringValue = jsonObject.get("dispatchDate").isString().stringValue();
		jsonValidatorFactory.createValidator("dispatchDate").validate(
				stringValue);
		dto.setDispatchDate(stringValue);

		stringValue = jsonObject.get("deliveryDate").isString().stringValue();
		jsonValidatorFactory.createValidator("deliveryDate").validate(
				stringValue);
		dto.setDeliveryDate(stringValue);

		stringValue = jsonObject.get("featureTransport").isString()
				.stringValue();
		jsonValidatorFactory.createValidator("featureTransport").validate(
				stringValue);
		dto.setFeatureTransport(stringValue);

		stringValue = jsonObject.get("typeOfLoadingUnloading").isString()
				.stringValue();
		jsonValidatorFactory.createValidator("typeOfLoadingUnloading")
				.validate(stringValue);
		dto.setTypeOfLoadingUnloading(stringValue);

		stringValue = jsonObject.get("briefDescriptionCargo").isString()
				.stringValue();
		jsonValidatorFactory.createValidator("briefDescriptionCargo").validate(
				stringValue);
		dto.setBriefDescriptionCargo(stringValue);

		return dto;
	}

	private String getJSONWithoutIllegalCharacters(String sourceJSON) {
		return sourceJSON.substring(sourceJSON.indexOf('{'),
				sourceJSON.indexOf('}') + 1);
	}

}
