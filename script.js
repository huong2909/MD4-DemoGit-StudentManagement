let display = document.getElementById("display");
let display1 = document.getElementById("display1");
let studentPage = 0;
let currentCategory = 0;

// show danh sach học sinh
function showList(page) {

    if (!page) {
        page = 0;
    }
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/students?page=" + page,
        success: function (data) {
            getStudent(data.content)
        },
        error: function () {
            alert('loi')
        }
    });
}

// form lấy dữ liệu toàn bộ học sinh
function getStudent(data) {
    let str = "";
    for (let i = 0; i < data.length; i++) {
        str += `    <tr>

                    <td class="left">${data[i].name}</td>
                    <td>${data[i].age}</td>
                    <td><img style="height: 100px;width: 100px" src="\\image\\${data[i].image}"></td>
                    <td>${data[i].mark}</td>
                    <td>${data[i].clazz.name}</td>
                    <td><button class="btn btn-success" onclick="deleteStudent(${data[i].id})"><i class="bi bi-trash3-fill"></i></button></td>
                   <td> <form enctype="multipart/form-data" id="form1">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal2" data-bs-whatever="@mdo" onclick="showEditForm(${data[i].id})">Edit</button>
                <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Edit Student</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="createProduct">
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Name</span>
                                        <input type="text" class="form-control"  aria-label="name" aria-describedby="basic-addon1" id="name1" name="name">
                                    </div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Age</span>
                                        <input type="number" class="form-control"  aria-label="price" aria-describedby="basic-addon1" id="age1" name="age">
                                    </div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Mark</span>
                                        <input type="number" class="form-control"  aria-label="price" aria-describedby="basic-addon1" id="mark1" name="mark">
                                    </div>
                                    <div class="input-group mb-3">
                                     <span class="input-group-text">Class</span>
                                        <select class="form-select" aria-label="Default select example" id="clazz1" name="clazz">
                                        <option selected>Clazz</option>
                                    </select>
                                    </div>
                                    <div class="input-group mb-3">
                                        <span class="input-group-text">Image</span>
                                        <input type="file" class="form-control"  aria-label="price" aria-describedby="basic-addon1" id="file1" name="file">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary" onclick="update(${data[i].id})">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form></td>
                </tr>`
    }
    getClass2();
    $('.button1').append(str);
}

// upload ảnh
function createStudent() {
    let form = document.getElementById("form");
    let data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "http://localhost:8080/api/students/upload",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function (data) {
            // document.getElementById('main').innerHTML = `${data.name}<img src="\\image\\${data.image}">`
            // showList()
            alert("Thêm thành công");
        }
    })
}

// load more
function loadMore() {
    studentPage = studentPage + 1;
    if (currentCategory == 0) {
        showList(studentPage)
    } else {
        findAllStudentByClass(currentCategory, studentPage);
    }
}

// show lớp
function findAllClass() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clazz",
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += `<tr>
            <td><a href="#" onclick="findAllStudentByClass(${data[i].id}); ">${data[i].name}</a></td>
        </tr>`;
            }
            document.getElementById("display1").innerHTML = content;
        }, error: function (error) {
            console.log(error);
        }
    })
}

// lấy tất cả học sinh trong một lớp
function findAllStudentByClass(id, page) {
    if (!page) {
        page = 0;
    }
    if (currentCategory != id) {
        document.getElementById("display").innerHTML = "";
        studentPage = 0; //set lại page về 0 để in ra từ đầu
    }
    currentCategory = id;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/clazz/view-clazz/" + id + "?page=" + page,
        success: function (data) {
            getStudent(data.content)
        }, error: function (error) {
            console.log(error);
        }
    })

}

// xóa học sinh
function deleteStudent(id) {
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/api/students/" + id,
        success: showList,
        error: function () {
            alert("Error!")
        }
    });
}

// tìm theo tên
function findAllByNameContaining() {
    let name = document.getElementById("searchByName").value;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/students/find?name=" + name,
        success: display,
        error: function () {
            alert("Error!")
        }
    });
}

//tìm theo khoảng điểm
function findAllByMarkBetween() {
    let from = document.getElementById("from").value;
    let to = document.getElementById("to").value;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/students/find-between?from=" + from + "&to=" + to,
        success: display,
        error: function () {
            alert("Error!")
        }
    });
}

//tìm top 3
function findTop3() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/students/find-top-3",
        success: display,
        error: function () {
            alert("Error!")
        }
    });
}

//lấy dữ liệu của lớp
function getClass(data) {
    let str = "";
    for (let i = 0; i < data.length; i++) {
        str += `<option value="${data[i].id}">${data[i].name}</option>`
    }
    return "<select id=\"clazz\">" + str + "</select>";
}

//show form sửa
function showEditForm(id) {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/students/" + id,
        success: function (data) {
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/api/clazz",
                success: function (clazz) {
                    // let str = `   <form enctype="multipart/form-data" id="form1">` +
                    //     `    Name<input value="${data.name}" type=\"text\" name= \"name\">\n` +
                    //     `    Age<input type=\"number\" name= \"age\" value="${data.age}">\n` +
                    //     getClass(clazz) +
                    //     `    Image<input type=\"file\" name=\"file\" value="${data.image}">\n` +
                    //     `    Mark<input type=\"number\" name=\"mark\" value="${data.mark}">\n` +
                    //     `    Mark<input type=\"hidden\" name=\"image\" value="${data.image}">\n` +
                    //     `    <button type="button" onclick='update(${data.id})'>Edit</button>`+
                    //     `    </form>`
                    // document.getElementById("showFormEdit").innerHTML = str;
                    $("#name1").val(data.name);
                    $("#age1").val(data.age);
                    $("#mark1").val(data.mark);
                    $("#clazz1").val(data.clazz.id);
                    $("#file1").val(data.image);

                }
            })
        }
    })
}

// sửa
function update(id) {
    $('#exampleModal2').modal('hide');
    let form = document.getElementById("form1");
    let data = new FormData(form);
    for (const value of data.values()) {
        console.log(value);
    }
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "http://localhost:8080/api/students/" + id,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000,
        success: function (data) {
            document.getElementById("display").innerHTML = "";
            showList();
        }
    })
}

//form select option
function getClass2() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/clazz`,
        success: function (data) {
            let str = "";
            for (let i = 0; i < data.length; i++) {
                str += `<option value="${data[i].id}">${data[i].name}</option>`
            }
            $("#clazz1").html(str);
        }
    })
}

showList();
findAllClass();
