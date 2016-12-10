/**
    * Created by $Yasi on 12/7/2016.
    */
function InvalidMsg(textbox) {

    if (textbox.value == '') {
        textbox.setCustomValidity('لطفا این فیلد را پر کنید.'); //Lütfen işaretli yerleri doldurunuz
    }
    else if(textbox.validity.typeMismatch){
        textbox.setCustomValidity('please enter a valid email address');
    }
    else {
        textbox.setCustomValidity('');
    }
    return true;
}

// <button onclick="window.location.href='/createGrantCondition.jsp'">Continue</button>