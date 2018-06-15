<#import "layouts/layout.ftl" as layout />
<@layout.mainLayout>
    <section class="content">
        <div class="col-md-9">
            <div class="box box-primary">
                <div class="box-body">
                    <form action="/item/itemCreate" method="post">
                        <div class="input-group margin">
                            <div class="form-group form-inline">
                                <label>商品名称</label>
                                <input type="text" class="form-control" name="name"/>
                            </div>
                            <div class="form-group form-inline">
                                <label>价格</label>
                                <input type="text" class="form-control" name="price"/>
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
