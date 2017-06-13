<#import "layouts/layout.ftl" as layout />
<@layout.mainLayout>
<div class="col-md-12">
    <div class="box box-primary">
        <div class="box-body">
            <form action="/order/orderCreate" method="post" enctype="multipart/form-data">
                <div class="input-group margin">
                    <div class="form-group">
                        <label>选择商品</label>
                        <select id="itemSelector" class="form-control" name="itemId">
                            <option v-for="item in itemList" v-bind:value="item.id"
                                    >{{item.name}}
                            </option>
                        </select>
                    </div>
                    <div class="form-group form-inline">
                        <label>用户手机号</label>
                        <input type="text" class="form-control" name="contactMobile"/>
                    </div>

                    <div class="form-group form-inline">
                        <label>请上传图</label>
                        <input type="file" class="form-control" name="img"/>
                    </div>
                    <div class="form-group form-inline">
                        <label>最终价格</label>
                        <input type="text" class="form-control" name="finalPrice"/>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary"
                            onclick="this.parentNode.submit(); return false;">创建新商品
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
//    $(function () {
//                $("#begin").datepicker().datepicker("option", {
//                    "dateFormat": "yy-mm-dd"
//                });
//                ;
//            }
//    );

    new Vue({
        el: "#itemSelector",
        data: {
            itemList:[]
        },
        created: function () {
            var _self = this;
            $.ajax({
                url: "/item/list",
                dataType: 'json',
                success: function (data) {
                    alert(JSON.stringify(data));
                    _self.itemList = data;
                }
            })

        }
    })
    ;
</script>
