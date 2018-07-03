<#import "layouts/layout.ftl" as layout />
<@layout.mainLayout>
<!-- Main content -->
<div class="row">
    <div class="col-xs-12">

        <div class="box">
            <div class="box-header">
                <h3 class="box-title">订单列表</h3>
            </div>
            <div class="box-body">
                <button class="btn btn-info btn-sm right"
                        onclick="javascript:location.href='/admin/order/orderCreatePG'">
                    创建新的订单
                </button>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive">
                <table id="example1" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>订单id</th>
                        <th>用户信息</th>
                        <th>商品信息</th>
                        <th>图</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th>更新基本信息</th>
                        <th>删除</th>
                        <th>关联画师</th>

                    </tr>
                    </thead>
                    <tbody id="tb">
                    <tr v-for="(order,index) in orderList">
                        <td>{{order.orderNumber}}</td>
                        <td>{{order.userName}}</td>
                        <td>{{order.itemName}}</td>
                        <td>
                            <button class="btn btn-info btn-sm right"
                                    v-on:click="startThumb(index)">
                                查看图片
                            </button>
                        </td>
                        <td>{{order.statusString}}</td>
                        <td>{{order.createtime}}</td>
                        <td>
                            <button class="btn btn-info btn-sm right" v-on:click="updateOrder(index)">更新
                            </button>
                        </td>
                        <td>
                            <button class="btn btn-info btn-sm right"
                                    v-on:click="deleteOrder(order.orderNumber)">
                                删除
                            </button>
                        </td>
                        <td>
                            <button v-if="order.painterId==-1" class="btn btn-info btn-sm right"
                                    v-on:click="setPainter(order.orderNumber)">
                                手动关联画师
                            </button>
                            <div v-else>
                                已经指派画师
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- /.box-body -->

        </div>
        <!-- /.box -->
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <div class="form-group form-inline">
                    <label>现在的订单状态是：{{order.statusString}}</label>

                </div>
                <div class="form-group form-inline">
                    <label>修改后的订单状态为：</label>
                    <select id="statusSelector" class="form-control" name="itemId">
                        <option v-for="status in statusList" v-bind:value="status.id"
                                >{{status.content}}
                        </option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" v-on:click="close()">Close</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="updateStatus()">Save
                    changes
                </button>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="thumbModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <img v-bind:src="imgUrl" width="200">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" v-on:click="close()">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="setPainterModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">订单关联画师</h4>
            </div>
            <div class="modal-body">
                <div class="box-body table-responsive">
                    <table id="painterListTable" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>画师id</th>
                            <th>画师姓名</th>
                            <th>创建时间</th>
                            <th>确认关联</th>
                        </tr>
                        </thead>
                        <tbody id="tb">
                        <tr v-for="(painter,index) in painterList">
                            <td>{{order.orderNumber}}</td>
                            <td>{{order.userName}}</td>
                            <td>{{order.itemName}}</td>
                            <td>
                                <button class="btn btn-info btn-sm right"
                                        v-on:click="startThumb(index)">
                                    查看图片
                                </button>
                            </td>
                            <td>{{order.statusString}}</td>
                            <td>{{order.createtime}}</td>
                            <td>
                                <button class="btn btn-info btn-sm right" v-on:click="updateOrder(index)">更新
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-info btn-sm right"
                                        v-on:click="deleteOrder(order.orderNumber)">
                                    删除
                                </button>
                            </td>
                            <td>
                                <button v-if="order.painterId==-1" class="btn btn-info btn-sm right"
                                        v-on:click="setPainter(order.orderNumber)">
                                    手动关联画师
                                </button>
                                <div v-else>
                                    已经指派画师
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</@layout.mainLayout>
    <!-- /.content -->
    <script type="text/javascript">
        var modal = new Vue({
                    el: "#myModal",
                    data: {
                        order: {},
                        statusList: []
                    },
                    created: function () {
                        var _self = this;
                        $.ajax({
                            url: "/admin/order/orderStatusList",
                            dataType: 'json',
                            success: function (data) {
                                _self.statusList = data;
                            }
                        })

                    },
                    methods: {
                        updateStatus: function () {
                            var orderId = this.order.id;
                            var status = $("#statusSelector").val();
                            $.ajax({
                                url: "/admin/order/orderUpdateStatus?id=" + orderId + "&status=" + status,
                                dataType: 'json',
                                success: function (data) {
                                    tb.$options.methods.reload();
                                }
                            })
                        },
                        close: function () {
                            $("#statusSelector").val(1);
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

        var painterListTable = new Vue({
            el: "#painterListTable",
            data: {
                painterList: []
            },
            created: function () {
            },
            methods: {
                loadPainterList: function () {


                }
            }
        });


        var tb = new Vue({
                    el: "#tb",
                    data: {
                        orderList: [],
                        statusList: []
                    },
                    created: function () {
                        var _self = this;
                        $.ajax({
                            url: "/admin/order/orderList",
                            dataType: 'json',
                            success: function (data) {
                                _self.orderList = data.orderList;
                            }
                        })

                    },
                    methods: {
                        updateOrder: function (index) {
                            modal.$data.order = this.orderList[index];
                            $('#myModal').modal();
                        },
                        reload: function () {
                            $.ajax({
                                url: "/admin/order/orderList",
                                dataType: 'json',
                                success: function (data) {
                                    tb.$data.orderList = data.orderList;
                                }
                            })
                        },
                        startThumb: function (index) {
                            thumbModal.$data.imgUrl = this.orderList[index].img;
                            $('#thumbModal').modal();
                        },
                        deleteOrder: function (orderNumber) {
                            var _self = this;
                            $.ajax({
                                url: "/admin/order/orderDelete",
                                data: {"orderNumber": orderNumber},
                                success: function () {
                                    alert("删除订单成功");
                                    for (var i = 0; i < _self.orderList.length; i++) {
                                        if (_self.orderList[i].orderNumber == orderNumber) {
                                            _self.orderList.splice(i, 1);
                                        }
                                    }
                                }
                            });
                        },
                        setPainter: function (orderNumber) {
                            $('#setPainterModal').modal();
                            painterListTable.$options.methods.loadPainterList();
                        }

                    }
                })
                ;


    </script>