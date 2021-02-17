var btns = [];
$(()=>{
    for (let i = 0; i < 16; ++i)
        btns[i] = $('#btn' + i);
    checkAppointment(Date.parse($('#date').val()));
});

/**
 * 
 * @param {number} date
 */
function checkAppointment(date) {
    $.post('/appointment/check', { date: date + 10 }, res => {
        refresh(res);
    });
}

/**
 *
 * @param {number} time
 * @param {boolean} cancel
 */
function appoint(time, cancel = null) {
    var date = Date.parse($('#date').val()) + 1;
    $.post('/appointment/appoint', { date: date, time: time, cancel:cancel }, res => {
        if (res == null || !res) {
            alert('（取消）预约失败');
            return;
        }
        if (cancel == null || !cancel) { // 预约
            for (let i = 0; i < 16; i++) {
                btns[i].attr('disabled', true);
                btns[i].text('不可预约');
            }
            btns[time].removeAttr('disabled');
            btns[time].css('background-color', 'green');
            btns[time].text('取消预约');
        } else { // 取消预约
            checkAppointment(date);
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
        for (let i = 0; i < btns.length; i++) {
            btns[i].removeAttr('disabled');
            btns[i].removeAttr('style');
            btns[i].text('预约');
        }
        return;
    }
    var idx = res.indexOf($.cookie('userid'));
    if (idx == -1) { // cur user not appoint
        for (let i = 0; i < btns.length; ++i) {
            if ("" === res[i]) {
                btns[i].removeAttr('disabled');
                btns[i].text('预约')
            }
            else {
                btns[i].attr('disabled', true);
                btns[i].text('不可预约');
            }
            btns[i].removeAttr('style');
        }
    } else { // cur user has appoint
        for (let i = 0; i < 16 ;++i) {
            btns[i].attr('disabled', true);
            btns[i].removeAttr('style');
            btns[i].text('不可预约');
        }
        btns[idx].removeAttr('disabled');
        btns[idx].css('background-color', 'green');
        btns[idx].text('取消预约');
    }
}
