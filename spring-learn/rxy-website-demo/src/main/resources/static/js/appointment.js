/**
 * 
 * @param {Date} date 
 */
function checkAppointment(date) {
    $.post('/appointment/check', {date: date.valueOf()}, res => {
        console.log(res);
    });
}
