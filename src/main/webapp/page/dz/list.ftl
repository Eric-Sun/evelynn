<#import "layouts/layout.ftl" as layout />
<@layout.mainLayout>
<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">Hover Data Table</h3>
            </div>
            <div class="box-body">
                <!-- Date -->
                <div class="form-group">
                    <label>Date:</label>

                    <div class="input-group date">

                        <input type="text" class="form-control pull-right" id="datepicker">
                        <button type="submit" class="btn btn-primary" onclick="javascript:aaa()">Submit</button>
                    </div>
                    <!-- /.input group -->
                </div>
                <!-- /.form group -->
            </div>

            <!-- Date range -->
            <!-- /.box-header -->
            <div class="box-body">
                <table id="aaa" class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>内容</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>

                </table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->

        <!-- /.box -->
    </div>
    <!-- /.col -->
</div>
<!-- /.row -->
</@layout.mainLayout>
<script type="text/javascript">
    $(function () {
        $('#aaa').DataTable({
            "processing": true,
            "serverSide": true,
            "searching": false,
            "lengthChange": false,
            "ajax": {
                "url": "/dz/listByDate"
            }
        });

        $('#datepicker').datepicker({
            autoclose: true
        });
    });

    function aaa() {
        var date = $("#datepicker").val();
        $('#aaa').DataTable({
            "destroy": true,
            "processing": true,
            "serverSide": true,
            "searching": false,
            "lengthChange": false,
            "ajax": {
                "url": "/dz/listByDate?date=" + date
            }
        });
    }

</script>
