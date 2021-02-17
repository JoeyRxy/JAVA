$(()=>{
    var $name = $.cookie('username');
    if ($name !== undefined) {
        $('#userinfo').text($name);
        $('#logoutid').removeAttr('hidden');
        if ($.cookie('admin')==="true") $('#menu').append('<li><a href="/admin">管理</a></li>');
        else $('#menu').append('<li><a href="/appointment">预约</a></li>');
    }
})

function logout() {
    $.post('/user/logout', () => {
        $.removeCookie('userid');
        $.removeCookie('username');
        $.removeCookie('admin');
        window.location.href = '/';
    });
}