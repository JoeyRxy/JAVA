
name_ls = [];

function getData(table_name) {
    table_name = table_name.toLowerCase();

    $.post('get_' + table_name, res => {
        var $tbody = $('#tbody');
        $tbody.html('');
        var head = "<tr>";
        var inputs = "<tr>";
        name_ls = []
        for (var k in res[0]) {
            name_ls.push(k);
            head += ("<td>" + k + "</td>");
            if (k !== 'id') inputs += ("<td><input type='text' id='" + k + "'></td>");
            else inputs += ('<td></td>')
        }
        head += "</tr>";
        inputs += ("<td><button onclick='insertData(\"" + table_name + "\")'>插入</button></td></tr>");
        var $thead = $('#thead');
        $thead.html('');
        $thead.append(head);
        $thead.append(inputs);
        res.forEach((map) => {
            var line = "<tr>";
            name_ls.forEach(k => {
                if (k === 'country')
                    line = line + '<td>' + map[k].code + '</td>';
                else
                    line = line + '<td>' + map[k] + '</td>';
            });
            line = line + "</tr>";
            $tbody.append(line);
        });
    }, 'json');
}

function insertData(table_name) {
    table_name = table_name.toLowerCase();

    var data = {};
    name_ls.forEach(k => {
        data[k] = $("#" + k).val();
    });

    $.post('ins_' + table_name, data, (res) => {
        if (!res)
            if (table_name === 'city') alert('在Country表中没有对应的country code');
            else alert('Country表中已经有了该country code');
        else
            getData(table_name);
    }, 'json');
}