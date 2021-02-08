$(()=>{
    var $name = $.cookie('username');
    if ($name !== undefined) {
        var $userinfo = $('#userinfo');
        $userinfo.text($name);
        var $parent = $userinfo.parent();
        $parent.append('<div onclick="logout();">登出</div>');
        $parent.append('<a href="/appointment">预约</a>')
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