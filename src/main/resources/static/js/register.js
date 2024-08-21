//Call the dataTables jQuery plugin
$(document).ready(function(){
    //on ready
});

async function registerUser(){

    let data = {}

    data.name =  document.getElementById('txtFirstName').value;
    data.surname = document.getElementById('txtLastName').value;
    data.email = document.getElementById('txtInputEmail').value;
    data.password = document.getElementById('txtInputPassword').value;

    let repeatPassword = document.getElementById('txtRepeatPassword').value;

    if(repeatPassword != data.password){
        alert('Passwords do not match. Please re-enter your password.');
        return;
    }

    const request = await fetch('api/user', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    alert('Your acount was created successfuly. Please login to access the website.');
    window.location.href='login.html';

}