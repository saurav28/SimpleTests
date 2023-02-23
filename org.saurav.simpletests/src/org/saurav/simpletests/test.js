var oBlob = null;
   var returnStatus;
   var xhttp = new window.XMLHttpRequest();
   xhttp.open("GET", downloadURL, true);  // download urlà content url
   xhttp.responseType = "blob";
   xhttp.onload = function () {
      var oExtension = sFileName.substr(sFileName.lastIndexOf('.') + 1);
  var oFileName = sFileName.substr(0, sFileName.lastIndexOf('.'));
  oBlob = xhttp.response;
  if (xhttp.status !== 400) {
                 sap.ui.core.util.File.save(oBlob, oFileName, oExtension, sMimeType, 'utf-8');
                         returnStatus = true;
          } else {
                         returnStatus = false;
          }
          resolve(returnStatus, xhttp.response);
 
               };
               xhttp.send();
});