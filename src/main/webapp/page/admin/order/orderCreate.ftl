<#import "layouts/layout.ftl" as layout />
<@layout.mainLayout>
<div class="col-md-12">
    <div class="box box-primary">
        <div class="box-body">
            <form id="createOrderForm" method="post" enctype="multipart/form-data">
                <div class="input-group margin">
                    <div class="form-group">
                        <label>选择商品</label>
                        <select class="form-control" name="itemId">
                            <option v-for="item in itemList" v-bind:value="item.id"
                                    >{{item.name}}
                            </option>
                        </select>
                    </div>
                    <div class="form-group form-inline">
                        <label>请上传图</label>
                        <input type="file" class="form-control" name="img"/>
                    </div>
                    <div class="form-group form-inline">
                        <label>最终价格</label>
                        <input type="text" class="form-control" name="finalPrice"/>
                    </div>
                    <div class="form-group form-inline">
                        <label>备注</label>
                        <input type="text" class="form-control" name="remark"/>
                    </div>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-primary"
                            v-on:click="createOrder()">创建新订单
                    </button>
                </div>
        </div>
    </div>
    </form>
    <!-- /input-group -->
</div>
</div>
</div>


</@layout.mainLayout>
<script type="text/javascript">


    new Vue({
        el: "#createOrderForm",
        data: {
            itemList: []
        },
        created: function () {
            var _self = this;
            $.ajax({
                url: "/admin/item/list",
                dataType: 'json',
                success: function (data) {
                    _self.itemList = data;
                }
            })

        },
        methods: {
            createOrder: function () {
                var formData = new FormData(document.getElementById("createOrderForm"));
                $.ajax({
                    url: "/admin/order/orderCreate",
                    type: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    dataType: 'json',
                    success: function (data) {
                        alert("创建订单成功");
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
</script>
