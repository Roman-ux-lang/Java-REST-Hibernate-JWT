// Call the dataTables jQuery plugin
$(document).ready(function() {
   getUsers();
   updateEmail();
  $('#users').DataTable();
});

function getHeaders(){

    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    }
}

function updateEmail(){
    document.getElementById('txt-email-user').outerHTML = localStorage.email;
}


async function getUsers(){
    const request = await  fetch('api/users',{
        method: 'GET',
        headers: getHeaders()
    });
    const users = await request.json();


    let listHTML = '';
    for(let user of users){
        txtPhone = user.phone == null ? '-' : user.phone;
        let deleteButton = '<a href="#" onclick="deleteUser('+ user.id +')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let userHTML = '<tr><td>'+user.id+'</td><td>'+user.name+' '+user.surname+'</td><td>'+user.email+'</td><td>'+txtPhone+'</td><td>' + deleteButton +'</td></tr>';
        listHTML += userHTML
    }

    document.querySelector('#users tbody').outerHTML = listHTML;

    console.log(users);
}

async function deleteUser(id){

    if(!confirm('¿Are you sure you want to delete user?')){
        return;
    }

    const request = await fetch('api/user/'+id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload();
}