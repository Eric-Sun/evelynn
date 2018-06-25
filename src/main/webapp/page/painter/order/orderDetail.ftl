<#import "layouts/layout.ftl" as layout />
<@layout.mainLayout>
    <#assign
    ctx=request.orderNumber>
<!-- Main content -->
<div class="row">
    <div class="col-xs-12">
        <div id="tb">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">画家订单列表 {ctx}</h3>
                </div>

                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <table id="example1" class="table table-bordered table-striped">
                        <tr>
                            <td>订单号：{{order.orderNumber}}</td>
                        </tr>
                        <tr>
                            <td>商品名称：{{order.itemName}}</td>
                        </tr>
                        <tr>
                            <td>下单时间：{{order.createtime}}</td>
                        </tr>
                        <tr>
                            <td>用户上传图：
                                <button class="btn btn-info btn-sm right"
                                        v-on:click="startThumb(index)">
                                    {{order.img}}
                            </td>
                            </button>
                        </tr>
                        <tr>
                            <td>订单状态：{{order.status}}</td>
                        </tr>
                        <tr>
                            <td>用户备注：{{order.remark}}</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">操作记录</h3>
                </div>

                <div class="box-body table-responsive">
                    <table id="example1" class="table table-bordered table-striped">
                        <tbody id="tb">
                        <thead>
                        <td>操作者</td>
                        <td>订单号</td>
                        <td>操作类型</td>
                        <td>操作内容</td>
                        <td>操作时间</td>
                        </thead>
                        <tr v-for="(record,index) in actionRecordList">
                            <td>{{record.accountId}}</td>
                            <td>{{record.orderNumber}}</td>
                            <td>{{record.actionType}}</td>
                            <td>{{record.content}}</td>
                            <td>{{record.createtime}}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->

            </div>
            <!-- /.box -->

            <div class="box">
                <button class="btn btn-info btn-sm right"
                        v-on:click="openRecordCreateModal()"> 增加记录
                </button>

                <button type="button" class="btn btn-primary"
                        v-on:click="finishOrder()">完结订单
                </button>
            </div>
        </div>
    </div>
</div>


<!-- 创建订单操作记录 -->
<div class="modal fade" id="recordCreateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <div class="box-body">
                    <form id="recordCreateForm" method="post">
                        <div class="input-group margin">

                            <div class="form-group form-inline">
                                <label>请上传图</label>
                                <input id="img" type="file" class="form-control" name="img"/>
                            </div>
                            <div class="form-group form-inline">
                                <label>备注</label>
                                <input id="remark" type="text" class="form-control" name="remark"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-primary"
                                    v-on:click="createRecord()">增加记录
                            </button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</@layout.mainLayout>
<!-- /.content -->
<script type="text/javascript">

    var recordCreateModal = new Vue({
                el: "#recordCreateModal",
                data: {},
                methods: {
                    createRecord: function () {
                        var formData = new FormData(document.getElementById("recordCreateForm"));
                        $.ajax({
                            url: "/painter/order/recordCreate",
                            type: "POST",
                            data: formData,
                            processData: false,
                            contentType: false,
                            dataType: 'json',
                            success: function (data) {
                                $('#recordCreateModal').modal("hide");
                                tb.$options.methods.reload();
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                alert(XMLHttpRequest.responseText);
                                alert("error:" + textStatus);
                                alert("error1:" + errorThrown);
                            }
                        });
                    }
                }
            })
            ;

    var thumbModal = new Vue({
                el: "#thumbModal",
                data: {
                    imgUrl: ""
                },
                created: function () {
                },
                methods: {
                    close: function () {
                        $("#statusSelector").val(1);
                    }
                }
            })
            ;


    var tb = new Vue({
                el: "#tb",
                data: {
                    order: [],
                    actionRecordList: []
                },
                created: function () {
                    var orderNumber = getQueryString("orderNumber");
                    var _self = this;
                    $.ajax({
                        url: "/painter/order/orderDetail",
                        data: {"orderNumber": orderNumber},
                        dataType: 'json',
                        success: function (data) {
                            _self.order = data.order;
                            _self.actionRecordList = data.order.actionRecordList
                        }
                    })

                },
                methods: {
                    updateOrder: function (index) {
                        modal.$data.order = this.orderList[index];
                        $('#myModal').modal();
                    },
                    reload: function () {
                        var orderNumber = getQueryString("orderNumber");
                        var _self = this;
                        $.ajax({
                            url: "/painter/order/orderDetail",
                            data: {"orderNumber": orderNumber},
                            dataType: 'json',
                            success: function (data) {
                                tb.$set(tb.$data, "order", data.order);
                                tb.$set(tb.$data, "actionRecordList", data.order.actionRecordList);
                            }
                        })
                    },
                    startThumb: function (index) {
                        thumbModal.$data.imgUrl = this.orderList[index].img;
                        $('#thumbModal').modal();
                    },
                    openRecordCreateModal: function () {
                        $('#recordCreateModal').modal();
                    },
                    finishOrder: function () {
                        var orderNumber = getQueryString("orderNumber");
                        $.ajax({
                            url: "/painter/order/updateStatus",
                            data:{
                                "orderNumber": orderNumber,
                                "status": 3
                            },
                            dataType: "json",
                            success: function (data) {
                                alert("结束订单成功");
                            },
                            error: function () {
                                alert("结束订单失败");
                            }
                        })
                    }


                }
            })
            ;


</script>