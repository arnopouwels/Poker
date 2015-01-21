function validateReg()
{
    var form = $( "form" ).first();
    var username = form.find('input[name="username"]').val();
    var pw1 = form.find('input[name="password"]').val();
    var pw2 = form.find('input[name="repassword"]').val();

    if(username ==="" || pw1==="" || pw2==="")//check that there isn't an empty field
    {
        alert("Empty field");
        return false;
    }
    else if(pw1!==pw2)// check that the passwords match
    {
        alert("Passwords do not match");
        return false
    }
    else
        return true;
}

function passwordStrength(password)
{
    var desc = new Array();
    desc[0] = "Very Weak";
    desc[1] = "Weak";
    desc[2] = "Better";
    desc[3] = "Medium";
    desc[4] = "Strong";
    desc[5] = "Strongest";

    var score   = 0;

    //if password bigger than 6 give 1 point
    if (password.length > 6) score++;

    //if password has both lower and uppercase characters give 1 point
    if ( ( password.match(/[a-z]/) ) && ( password.match(/[A-Z]/) ) ) score++;

    //if password has at least one number give 1 point
    if (password.match(/\d+/)) score++;

    //if password has at least one special caracther give 1 point
    if ( password.match(/.[!,@,#,$,%,^,&,*,?,_,~,-,(,)]/) )	score++;

    //if password bigger than 12 give another 1 point
    if (password.length > 12) score++;

     document.getElementById("passwordDescription").innerHTML = desc[score];
     //document.getElementById("passwordStrength").className = "strength" + score;

}