<#import "layouts/layout.ftl" as layout />
<@layout.mainLayout>
    <section class="content">
        <div class="col-md-9">
            <div class="box box-primary">
                <div class="box-body">
                    <form action="/item/itemUpdate" method="post">
                        <div class="input-group margin">

                            <div class="form-group form-inline">
                                <label>商品id</label>
                                <input type="text" class="form-control" name="id" value="${item.id}"/>
                            </div>
                            <div class="form-group form-inline">
                                <label>商品名称</label>
                                <input type="text" class="form-control" name="name" value="${item.name}"/>
                            </div>
                            <div class="form-group form-inline">
                                <label>商品价格</label>
                                <input type="text" class="form-control" name="price" value="${item.price}"/>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary"
                                        onclick="this.parentNode.submit(); return false;">更新角色
                                </button>
                            </div>
                        </div>
                </div>
                </form>
                <!-- /input-group -->
            </div>
        </div>
        </div>
    </section>
</@layout.mainLayout>
<script type="text/javascript">
    $(function () {
                $("#begin").datepicker().datepicker("option", {
                    "dateFormat": "yy-mm-dd"
                });
                ;
            }
    );
</script>
