/**
 * 
 * @param {number} date
 */
function checkAppointment(date) {
    $.post('/appointment/check', { date: date + 1 }, res => {
        refresh(res);
    });
}

/**
 *
 * @param {number} time
 */
function appoint(time) {
    var $date = $('#date');
    $.post('/appointment/appoint', { date: Date.parse($date.val()) + 1, time: time }, res => {
        if (res) {
            for (let i = 0; i < 16; i++) {
                $('#btn' + i).attr('disabled', true);
            }
            $('#btn' + time).css('background-color', 'green');
        }
    });
}

/**
 * 
 * @param {Array} res 
 */
function refresh(res) {
    console.log(res);
    if (res == null || res == "") { // There's no appointment this day
        for (let i = 0; i < 16; i++) {
            let $btni = $('#btn' + i);
            $btni.removeAttr('disabled');
            $btni.removeAttr('style');
        }
        return;
    }
    var idx = res.indexOf($.cookie('userid'));
    if (idx == -1) { // cur user not appoint
        for (let i = 0; i < 16; ++i) {
            let $btni = $('#btn' + i);
            if ("" === res[i]) $btni.removeAttr('disabled');
            else $btni.attr('disabled', true);
            $btni.removeAttr('style');
        }
    } else { // cur user has appoint
        for (let i = 0; i < 16 ;++i) {
            let $btni = $('#btn' + i);
            $btni.attr('disabled', true);
            $btni.removeAttr('style');
        }
        $('#btn' + idx).css('background-color', 'green');
    }
}
