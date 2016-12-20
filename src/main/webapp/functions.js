/**
 * Created by $Yasi on 12/7/2016.
 */

function InvalidMsg(textbox) {

    if (textbox.value == '') {
        textbox.setCustomValidity('لطفا این فیلد را پر کنید.'); //Lütfen işaretli yerleri doldurunuz
    }
    else if (textbox.validity.typeMismatch) {
        textbox.setCustomValidity('please enter a valid email address');
    }
    else {
        textbox.setCustomValidity('');
    }
    return true;
}

function retrieveCustomer() {
    var xhttp = new XMLHttpRequest();
    var customerNumber = document.getElementById("customerNumber").value;
    var customer;
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //document.getElementById("demo").innerHTML = this.responseText;
            customer = JSON.parse(xhttp.response);
            if (customer.firstName != null) {
                document.getElementById("nullCustomer").style.display = "none";
                var input1 = document.getElementById("firstName");
                input1.setAttribute("value", customer.firstName);
                var input2 = document.getElementById("lastName");
                input2.setAttribute("value", customer.lastName);
                document.getElementById("showCustomer").style.display = "inherit";
                document.getElementById("dropDown").style.display = "inherit";
                document.getElementById("submit").style.display = "inherit";

            } else if (customer.error != null) {
                document.getElementById("showCustomer").style.display = "none";
                document.getElementById("dropDown").style.display = "none";
                document.getElementById("submit").style.display = "none";
                document.getElementById("nullCustomer").innerHTML = customer.error;
                document.getElementById("nullCustomer").style.display = "inherit";
            } else {
                document.getElementById("showCustomer").style.display = "none";
                document.getElementById("dropDown").style.display = "none";
                document.getElementById("submit").style.display = "none";
                document.getElementById("nullCustomer").innerHTML = " مشتری با این شماره وجود ندارد.";
                document.getElementById("nullCustomer").style.display = "inherit";
            }
        }
    };
    xhttp.open("GET", "/RetrieveCustomer?action=retrieve-customer&customerNumber=" + customerNumber, true);
    xhttp.send();
}

function inputName(i) {
    switch (i) {
        case 0:
            return "grantName";
        case 1:
            return "minPeriod";
        case 2:
            return "maxPeriod";
        case 3:
            return "minAmount";
        case 4:
            return "maxAmount";
        default: null;
    }
}
function insertRow() {
    var table = document.getElementById("grants");
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    for (var i=0; i<5;i++){
        var cell = row.insertCell(i);
        cell.innerHTML = "<input type=\"data\" name='"+inputName(i)+"'  oninvalid=\"InvalidMsg(this);\"required />";
    }
    var deleteCell=row.insertCell(5);
    deleteCell.innerHTML="<input type=\"button\" value = \"حذف\" onClick=\"deleteRow(this)\">";
    //document.getElementById("rowCount").attributes[2].value = rowCount;
}
function deleteRow(obj) {
    var index = obj.parentNode.parentNode.rowIndex;
    var table = document.getElementById("grants");
    table.deleteRow(index);
}

    //var age = document.getElementById("age");
   // var table = document.getElementById("myTableData");
   // row.insertCell(0).innerHTML= '<input type="button" value = "Delete" onClick="Javacsript:deleteRow(this)">';
   // row.insertCell(1).innerHTML= myName.value;
   // row.insertCell(2).innerHTML= age.value;

// <button onclick="window.location.href='/createGrantCondition.jsp'">Continue</button>