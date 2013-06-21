package com.ipoint.cargo4me.client.application.loadunload;

import com.ipoint.cargo4me.client.util.LoadUserDataCallBackHandler;

public class UploadPanel {

	public static native void panel(
			LoadUserDataCallBackHandler loadUserDataCallBackHandler) /*-{

		var files = $wnd.document.getElementById('files').files;
		if (!files.length) {
			alert('Please select a file!');
			return;
		}

		var file = files[0];
		var start = 0;
		var stop = file.size - 1;

		var reader = new FileReader();

		reader.onloadend = function(evt) {
			if (evt.target.readyState == FileReader.DONE) {
				loadUserDataCallBackHandler.@com.ipoint.cargo4me.client.util.LoadUserDataCallBackHandler::setUsersJSON(Ljava/lang/String;)(evt.target.result)
			}
		};

		var blob = file.slice(start, stop + 1);
		reader.readAsBinaryString(blob);

	}-*/;
}