/**
 * 
 * @param {boolean} byid 
 * @param {string} val 
 */
function search(byid, val) {
    if (val === null || val === undefined || val === '') {
        location.reload();
        return;
    }
    console.log(byid);
    console.log(val);
    $.ajax({
        url: '/admin/search',
        type: 'POST',
        data: { byid: byid, userid: val, username: val },
        success: refreshTbl,
        error: function () {
            alert('failed');
        },
        dataType: 'json'
    });
}

/**
 * 
 * @param {Array<JSON>} res 
 */
function refreshTbl(res) {
    if (res == null || res == undefined || res.length == 0) {
        alert('无搜索结果');
        return;
    }
    let $tbl = $('#usertbl');
    $tbl.html('');
    for (let i = 0; i < res.length; i++) {
        const e = res[i];
        $tbl.append('<tr>' + 
            '<td>' + (i + 1) + '</td>' +
            '<td>' + e.username + '</td>' +
            '<td>' + e.userid + '</td>' +
            '<td>' + e.note + '</td>' +
        '</tr>');
    }
    $('#navbar').hide();
}

/**
 * 
 * @param {HTMLElement} e 
 */
function show(e) {
    e.children.item(1).classList.add('show');
}

/**
 * 
 * @param {HTMLElement} e 
 */
function hide(e) {
    e.lastElementChild.classList.remove('show');
}

