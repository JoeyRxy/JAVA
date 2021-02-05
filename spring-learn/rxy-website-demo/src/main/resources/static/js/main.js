$(()=>{
    var $name = $.cookie('username');
    $('#userinfo').text($name === undefined ? 'Login' : $name);
})
