<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>

<html>
<head>
    <title>Thông tin tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home Admin</a>
                </li>
                <li class="active">Danh sách toàn nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content" style="font-family: 'Times New Roman', Times, serif;">
            <div class="page-header">
                <h1>
                    Thông tin tòa nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <!-- Main page -->
            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <form:form modelAttribute="building" id="form-edit">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-xs-3">Tên tòa nhà</label>
                                <div class="col-xs-9">
                                    <form:input path="name" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Quận</label>
                                <div class="col-xs-3">
                                    <form:select path="district" class="form-control">
                                        <form:option value="">--Chọn quận--</form:option>
                                        <form:options items="${districtCode}"></form:options>
                                    </form:select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Phường</label>
                                <div class="col-xs-9">
                                    <form:input path="ward" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Đường</label>
                                <div class="col-xs-9">
                                    <form:input path="street" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Kết cấu</label>
                                <div class="col-xs-9">
                                    <form:input path="structure" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Số tầng hầm</label>
                                <div class="col-xs-9">
                                    <form:input path="numberOfBasement" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Diện tích sàn</label>
                                <div class="col-xs-9">
                                    <form:input path="floorArea" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Hướng</label>
                                <div class="col-xs-9">
                                    <form:input path="direction" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Hạng</label>
                                <div class="col-xs-9">
                                    <form:input path="level" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Diện tích thuê</label>
                                <div class="col-xs-9">
                                    <form:input path="rentArea" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Giá thuê</label>
                                <div class="col-xs-9">
                                    <form:input path="rentPrice" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Mô tả giá</label>
                                <div class="col-xs-9">
                                    <form:input path="rentPriceDescription" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Phí dịch vụ</label>
                                <div class="col-xs-9">
                                    <form:input path="serviceFee" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Phí ô tô</label>
                                <div class="col-xs-9">
                                    <form:input path="carFee" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Phí mô tô</label>
                                <div class="col-xs-9">
                                    <form:input path="motoFee" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Phí ngoài giờ</label>
                                <div class="col-xs-9">
                                    <form:input path="overtimeFee" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Tiền điện</label>
                                <div class="col-xs-9">
                                    <form:input path="electricityFee" class="form-control"/>
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-xs-3">Đặt cọc</label>
                                <div class="col-xs-9">
                                    <form:input path="deposit" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Thanh toán</label>
                                <div class="col-xs-9">
                                    <form:input path="payment" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Thời hạn thuê</label>
                                <div class="col-xs-9">
                                    <form:input path="rentTime" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Thời gian trang trí</label>
                                <div class="col-xs-9">
                                    <form:input path="decorationTime" class="form-control"/>
                                </div>
                            </div>

                            <form:input type = "hidden" path="id" class="form-control"/>

                            <div class="form-group">
                                <label class="col-xs-3">Tên quản lí</label>
                                <div class="col-xs-9">
                                    <form:input path="managerName" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">SĐT quản lí</label>
                                <div class="col-xs-9">
                                    <form:input path="managerPhone" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Loại tòa nhà</label>
                                <div class="col-xs-9">
                                    <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Phí môi giới</label>
                                <div class="col-xs-9">
                                    <input type="number" id="brokerageFees" name="brokerageFees"
                                           class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3">Ghi chú</label>
                                <div class="col-xs-9">
                                    <input type="text" id="note" name="note" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                <c:if test="${empty building.id}">
                                <button class="btn btn-primary" type="button" id="btnAddBuilding">Thêm tòa
                                        nhà</button>
                                </c:if>
                                <c:if test="${not empty building.id}">
                                <button class="btn btn-warning" type="button" id="btnAddBuilding">Sửa tòa
                                        nhà</button>
                                </c:if>
                                <a href="/admin/building-list">
                                   <button class="btn btn-primary" type="button">Hủy thao tác</button>
                                </a>
                                    <!-- button trong form phải đặt type=button -->

                                </div>
                            </div>
                        </form>
                    </div>
                </form:form>
            </div>
        </div><!-- /.page-content -->
    </div>

</div><!-- /.main-content -->
<script>
    $('#btnAddBuilding').click(function () {
        var json = {};
        var typeCode = []
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, it) {
            if (it.name != 'typeCode') json["" + it.name + ""] = it.value;
            else typeCode.push(it.value);
        })
        json['typeCode'] = typeCode;
        console.log("ok")
        if (json[name]==''){
            return alert("Ten toa nha ko duoc thieu")
        }
        else if(json['typeCode'] == ""){
            return alert("Loai toa nha ko duoc thieu")
        }
        else{
            addOrUpdateBuilding(json)
        }
    });

    // ajax để gửi dữ liệu từ phía client xuống server
    function addOrUpdateBuilding(data){
        $.ajax({
            type:"POST",
            url:"/api/buildings",
            data:JSON.stringify(data),
            dataType:"text",
            contentType:"application/json",
            success : function(response){
                console.log("Success");
                alert(response);
                window.location.replace("/admin/building-list")
            },
            error : function(response){
                console.log("Failed");
                console.log(response);
                alert("Create Or Update Building Done Success")
            }
        });
    }
</script>
</body>
</html>
