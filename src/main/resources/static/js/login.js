$(document).ready(function(){
    // on ready
});

async function loginSession(){

    let data = {};

    data.email = document.getElementById('txtInputEmail').value;
    data.password = document.getElementById('txtInputPassword').value;

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    const response = await request.text();
    console.log(response);

    if(response != 'Fail'){
        localStorage.token = response;
        localStorage.email = data.email;
        window.location.href='users.html';
    }else{
        alert('Incorrect email or password, please try again.');
    }


}