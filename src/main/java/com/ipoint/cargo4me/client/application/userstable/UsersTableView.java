package com.ipoint.cargo4me.client.application.userstable;

import java.util.ArrayList;
import java.util.List;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.SimplePager;
import com.github.gwtbootstrap.client.ui.SimplePager.TextLocation;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.ipoint.cargo4me.shared.dto.CargoOwnerDTO;
import com.ipoint.cargo4me.shared.dto.CarrierDTO;
import com.ipoint.cargo4me.shared.dto.UserDTO;

public class UsersTableView extends ViewWithUiHandlers<UsersTableUiHandlers>
		implements UsersTablePresenter.MyView {

	private final Widget widget;

	ListDataProvider<UserDTO> dataProvider;

	Label noItemsTableCaption;

	public interface Binder extends UiBinder<Widget, UsersTableView> {
	}

	@UiField
	CheckBox checkBoxSelectAll;

	@UiField
	HTMLPanel tableContainer;

	CellTable<UserDTO> userTable;

	@UiField(provided = true)
	SimplePager pager;

	@Inject
	public UsersTableView(final Binder binder) {
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);

		widget = binder.createAndBindUi(this);
	}

	private void initCommonTableColumns() {

		// email
		Column<UserDTO, String> userEmailColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return object.getUserEmail();
			}
		};
		userEmailColumn.setSortable(true);
		userTable.addColumn(userEmailColumn, "Email");
		userTable.setColumnWidth(userEmailColumn, 30, Unit.PCT);

		// phone
		Column<UserDTO, String> contactPhoneColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return object.getContactPhone();
			}
		};
		contactPhoneColumn.setSortable(true);
		userTable.addColumn(contactPhoneColumn, "Контактный телефон");
		userTable.setColumnWidth(contactPhoneColumn, 30, Unit.PCT);

		// name
		Column<UserDTO, String> fullNameColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return object.getFullName();
			}
		};
		fullNameColumn.setSortable(true);
		userTable.addColumn(fullNameColumn, "Имя");
		userTable.setColumnWidth(fullNameColumn, 30, Unit.PCT);

		// type
		Column<UserDTO, String> typeColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return object.getType();
			}
		};
		typeColumn.setSortable(false);

		userTable.addColumn(typeColumn, "Тип");
		userTable.setColumnWidth(typeColumn, 20, Unit.PCT);

		// cost
		Column<UserDTO, String> costColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return String.valueOf(object.getCost());
			}
		};
		costColumn.setSortable(false);

		userTable.addColumn(costColumn, "Цена");
		userTable.setColumnWidth(costColumn, 20, Unit.PCT);

		// truckCategory
		Column<UserDTO, String> truckCategoryColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return object.getTruckCategory();
			}
		};
		truckCategoryColumn.setSortable(false);
		userTable.addColumn(truckCategoryColumn, "Категория машины");
		userTable.setColumnWidth(truckCategoryColumn, 50, Unit.PCT);

		// comment
		Column<UserDTO, String> commentColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return String.valueOf(object.getComment());
			}
		};
		commentColumn.setSortable(false);

		userTable.addColumn(commentColumn, "Комментарий");
		userTable.setColumnWidth(commentColumn, 50, Unit.PCT);
	}

	private void initCarrierTableColumns() {

		// cityOfRegistry
		Column<UserDTO, String> cityOfRegistryColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getCityOfRegistry();
			}
		};
		cityOfRegistryColumn.setSortable(false);
		userTable.addColumn(cityOfRegistryColumn,
				"Город регистрации транспорта");
		userTable.setColumnWidth(cityOfRegistryColumn, 50, Unit.PCT);

		// name
		Column<UserDTO, String> nameColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getName();
			}
		};
		nameColumn.setSortable(false);
		userTable.addColumn(nameColumn, "Краткое описание транспорта");
		userTable.setColumnWidth(nameColumn, 50, Unit.PCT);

		// averageRatingPerKm
		Column<UserDTO, String> averageRatingPerKmColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getAverageRatingPerKm();
			}
		};
		averageRatingPerKmColumn.setSortable(false);
		userTable.addColumn(averageRatingPerKmColumn, "Ставка  руб/км");
		userTable.setColumnWidth(averageRatingPerKmColumn, 50, Unit.PCT);

		// averageRatingPerHour
		Column<UserDTO, String> averageRatingPerHourColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getAverageRatingPerHour();
			}
		};
		averageRatingPerHourColumn.setSortable(false);
		userTable.addColumn(averageRatingPerHourColumn, "Ставка руб/час");
		userTable.setColumnWidth(averageRatingPerHourColumn, 50, Unit.PCT);

		// carOwner
		Column<UserDTO, String> carOwnerColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getCarOwner();
			}
		};
		carOwnerColumn.setSortable(false);
		userTable.addColumn(carOwnerColumn, "Владелец авто");
		userTable.setColumnWidth(carOwnerColumn, 50, Unit.PCT);

		// driver
		Column<UserDTO, String> driverColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getDriver();
			}
		};
		driverColumn.setSortable(false);
		userTable.addColumn(driverColumn, "Водитель");
		userTable.setColumnWidth(driverColumn, 50, Unit.PCT);

		// carRegistrationData
		Column<UserDTO, String> carRegistrationDataColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getCarRegistrationData();
			}
		};
		carRegistrationDataColumn.setSortable(false);
		userTable.addColumn(carRegistrationDataColumn, "Регистрационный номер");
		userTable.setColumnWidth(carRegistrationDataColumn, 50, Unit.PCT);

		// cargoType
		Column<UserDTO, String> cargoTypeColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getCargoType();
			}
		};
		cargoTypeColumn.setSortable(false);
		userTable.addColumn(cargoTypeColumn, "Тип груза");
		userTable.setColumnWidth(cargoTypeColumn, 50, Unit.PCT);

		// typeOfShipping
		Column<UserDTO, String> typeOfShippingColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getTypeOfShipping();
			}
		};
		typeOfShippingColumn.setSortable(false);
		userTable.addColumn(typeOfShippingColumn, "Тип перевозок");
		userTable.setColumnWidth(typeOfShippingColumn, 50, Unit.PCT);

		// typeOfCar
		Column<UserDTO, String> typeOfCarColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CarrierDTO) object).getTypeOfCar();
			}
		};
		typeOfCarColumn.setSortable(false);
		userTable.addColumn(typeOfCarColumn, "Тип машины");
		userTable.setColumnWidth(typeOfCarColumn, 50, Unit.PCT);

	}

	private void initCargoOwnerTableColumns() {

		// volume
		Column<UserDTO, String> volumeColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getVolume();
			}
		};
		volumeColumn.setSortable(false);
		userTable.addColumn(volumeColumn, "Объем (в м3)");
		userTable.setColumnWidth(volumeColumn, 50, Unit.PCT);

		// weight
		Column<UserDTO, String> weightColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getWeight();
			}
		};
		weightColumn.setSortable(false);
		userTable.addColumn(weightColumn, "Масса (в кг)");
		userTable.setColumnWidth(weightColumn, 50, Unit.PCT);

		// from
		Column<UserDTO, String> fromColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getDispatchOther();
			}
		};
		fromColumn.setSortable(false);
		userTable.addColumn(fromColumn, "Адрес отправки");
		userTable.setColumnWidth(fromColumn, 50, Unit.PCT);

		// to
		Column<UserDTO, String> toColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getDeliveryOther();
			}
		};
		toColumn.setSortable(false);
		userTable.addColumn(toColumn, "Адрес доставки");
		userTable.setColumnWidth(toColumn, 50, Unit.PCT);

		// dispatchTime
		Column<UserDTO, String> dispatchTimeColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getDispatchDate();
			}
		};
		dispatchTimeColumn.setSortable(false);
		userTable.addColumn(dispatchTimeColumn, "Дата и время отправки");
		userTable.setColumnWidth(dispatchTimeColumn, 50, Unit.PCT);

		// deliveryTime
		Column<UserDTO, String> deliveryTimeColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getDeliveryDate();
			}
		};
		deliveryTimeColumn.setSortable(false);
		userTable.addColumn(deliveryTimeColumn, "Дата и время доставки");
		userTable.setColumnWidth(deliveryTimeColumn, 50, Unit.PCT);

		// featureTransport
		Column<UserDTO, String> featureTransportColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getFeatureTransport();
			}
		};
		featureTransportColumn.setSortable(false);
		userTable.addColumn(featureTransportColumn, "Особенности транспорта");
		userTable.setColumnWidth(featureTransportColumn, 50, Unit.PCT);

		// typeOfLoadingUnloading
		Column<UserDTO, String> loadingUnloadingTypeColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getTypeOfLoadingUnloading();
			}
		};
		loadingUnloadingTypeColumn.setSortable(false);
		userTable
				.addColumn(loadingUnloadingTypeColumn, "Тип погрузки/выгрузки");
		userTable.setColumnWidth(loadingUnloadingTypeColumn, 50, Unit.PCT);

		// briefDescriptionCargo
		Column<UserDTO, String> cargoDescriptionColumn = new Column<UserDTO, String>(
				new EditTextCell()) {
			@Override
			public String getValue(UserDTO object) {
				return ((CargoOwnerDTO) object).getBriefDescriptionCargo();
			}
		};
		cargoDescriptionColumn.setSortable(false);
		userTable.addColumn(cargoDescriptionColumn, "Краткое описание груза");
		userTable.setColumnWidth(cargoDescriptionColumn, 50, Unit.PCT);

	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setUserTableData(List<UserDTO> users) {
		userTable = new CellTable<UserDTO>();
		userTable.setWidth("100%");
		noItemsTableCaption = new Label("Список пуст");
		noItemsTableCaption.setStyleName("noItemsCaption");
		userTable.setEmptyTableWidget(noItemsTableCaption);
		pager.setDisplay(userTable);
		final MultiSelectionModel<UserDTO> selectionModel = new MultiSelectionModel<UserDTO>();
		userTable.setSelectionModel(selectionModel,
				DefaultSelectionEventManager.<UserDTO> createCheckboxManager());
		dataProvider = new ListDataProvider<UserDTO>();
		dataProvider.addDataDisplay(userTable);
		tableContainer.clear();
		tableContainer.add(userTable);

		Column<UserDTO, Boolean> checkColumn = new Column<UserDTO, Boolean>(
				new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(UserDTO object) {
				return selectionModel.isSelected(object);
			}
		};
		userTable.addColumn(checkColumn,
				SafeHtmlUtils.fromSafeConstant("<br/>"));
		userTable.setColumnWidth(checkColumn, 50, Unit.PX);

		initCommonTableColumns();
		if (users.get(0) instanceof CarrierDTO) {
			initCarrierTableColumns();
		} else {
			initCargoOwnerTableColumns();
		}

		clearUsersTable();
		List<UserDTO> list = dataProvider.getList();
		for (UserDTO user : users) {
			list.add(user);
		}
		dataProvider.flush();
		selectAll(true);
		setCheckBoxSelectAllAvailable(true);
	}

	@Override
	public List<UserDTO> getSelectedUsers() {
		List<UserDTO> selectedUsers;
		selectedUsers = new ArrayList<UserDTO>(
				((MultiSelectionModel<UserDTO>) userTable.getSelectionModel())
						.getSelectedSet());
		return selectedUsers;
	}

	private void selectAll(boolean selected) {
		for (UserDTO object : dataProvider.getList())
			userTable.getSelectionModel().setSelected(object, selected);
	}

	public void clearUsersTable() {
		dataProvider.getList().clear();
		setCheckBoxSelectAllAvailable(false);
	}

	@UiHandler("checkBoxSelectAll")
	public void onCheckBoxSelectAllClicked(ClickEvent clickEvent) {
		if (checkBoxSelectAll.getValue()) {
			checkBoxSelectAll.setText("Отменить выбор");
			selectAll(true);
		} else {
			checkBoxSelectAll.setText("Выбрать все");
			selectAll(false);
		}
	}

	private void setCheckBoxSelectAllAvailable(boolean available) {
		checkBoxSelectAll.setVisible(available);
		checkBoxSelectAll.setValue(available);
	}

}
