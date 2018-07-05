<#setting number_format="#">
<#macro mainLayout>
<#assign sec= JspTaglibs["/WEB-INF/security.tld"]/>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Cosy Design 管理后台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
<#--<!-- Font Awesome &ndash;&gt;-->
<#--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">-->
<#--<!-- Ionicons &ndash;&gt;-->
<#--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">-->
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">

<#--<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries &ndash;&gt;-->
<#--<!-- WARNING: Respond.js doesn't work if you view the page via file:// &ndash;&gt;-->
<#--<!--[if lt IE 9]>-->
<#--<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>-->
<#--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>-->
<#--<![endif]&ndash;&gt;-->
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <header class="main-header">

        <!-- Logo -->
        <a class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-lg"><b>Cosy Design</b></span>
            <!-- logo for regular state and mobile devices -->
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <#--<li class="dropdown messages-menu">-->
                        <#--<!-- Menu toggle button &ndash;&gt;-->
                        <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                            <#--<i class="fa fa-envelope-o"></i>-->
                            <#--<span class="label label-success">4</span>-->
                        <#--</a>-->
                        <#--<ul class="dropdown-menu">-->
                            <#--<li class="header">You have 4 messages</li>-->
                            <#--<li>-->
                                <#--<!-- inner menu: contains the messages &ndash;&gt;-->
                                <#--<ul class="menu">-->
                                    <#--<li><!-- start message &ndash;&gt;-->
                                        <#--<a href="#">-->
                                            <#--<div class="pull-left">-->
                                                <#--<!-- User Image &ndash;&gt;-->
                                                <#--<img src="dist/img/user2-160x160.jpg" class="img-circle"-->
                                                     <#--alt="User Image">-->
                                            <#--</div>-->
                                            <#--<!-- Message title and timestamp &ndash;&gt;-->
                                            <#--<h4>-->
                                                <#--Support Team-->
                                                <#--<small><i class="fa fa-clock-o"></i> 5 mins</small>-->
                                            <#--</h4>-->
                                            <#--<!-- The message &ndash;&gt;-->
                                            <#--<p>Why not buy a new awesome theme?</p>-->
                                        <#--</a>-->
                                    <#--</li>-->
                                    <#--<!-- end message &ndash;&gt;-->
                                <#--</ul>-->
                                <#--<!-- /.menu &ndash;&gt;-->
                            <#--</li>-->
                            <#--<li class="footer"><a href="#">See All Messages</a></li>-->
                        <#--</ul>-->
                    <#--</li>-->
                    <#--<!-- /.messages-menu &ndash;&gt;-->

                    <#--<!-- Notifications Menu &ndash;&gt;-->
                    <#--<li class="dropdown notifications-menu">-->
                        <#--<!-- Menu toggle button &ndash;&gt;-->
                        <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                            <#--<i class="fa fa-bell-o"></i>-->
                            <#--<span class="label label-warning">10</span>-->
                        <#--</a>-->
                        <#--<ul class="dropdown-menu">-->
                            <#--<li class="header">You have 10 notifications</li>-->
                            <#--<li>-->
                                <#--<!-- Inner Menu: contains the notifications &ndash;&gt;-->
                                <#--<ul class="menu">-->
                                    <#--<li><!-- start notification &ndash;&gt;-->
                                        <#--<a href="#">-->
                                            <#--<i class="fa fa-users text-aqua"></i> 5 new members joined today-->
                                        <#--</a>-->
                                    <#--</li>-->
                                    <#--<!-- end notification &ndash;&gt;-->
                                <#--</ul>-->
                            <#--</li>-->
                            <#--<li class="footer"><a href="#">View all</a></li>-->
                        <#--</ul>-->
                    <#--</li>-->
                    <#--<!-- Tasks Menu &ndash;&gt;-->
                    <#--<li class="dropdown tasks-menu">-->
                        <#--<!-- Menu Toggle Button &ndash;&gt;-->
                        <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown">-->
                            <#--<i class="fa fa-flag-o"></i>-->
                            <#--<span class="label label-danger">9</span>-->
                        <#--</a>-->
                        <#--<ul class="dropdown-menu">-->
                            <#--<li class="header">You have 9 tasks</li>-->
                            <#--<li>-->
                                <#--<!-- Inner menu: contains the tasks &ndash;&gt;-->
                                <#--<ul class="menu">-->
                                    <#--<li><!-- Task item &ndash;&gt;-->
                                        <#--<a href="#">-->
                                            <#--<!-- Task title and progress text &ndash;&gt;-->
                                            <#--<h3>-->
                                                <#--Design some buttons-->
                                                <#--<small class="pull-right">20%</small>-->
                                            <#--</h3>-->
                                            <#--<!-- The progress bar &ndash;&gt;-->
                                            <#--<div class="progress xs">-->
                                                <#--<!-- Change the css width attribute to simulate progress &ndash;&gt;-->
                                                <#--<div class="progress-bar progress-bar-aqua" style="width: 20%"-->
                                                     <#--role="progressbar" aria-valuenow="20" aria-valuemin="0"-->
                                                     <#--aria-valuemax="100">-->
                                                    <#--<span class="sr-only">20% Complete</span>-->
                                                <#--</div>-->
                                            <#--</div>-->
                                        <#--</a>-->
                                    <#--</li>-->
                                    <#--<!-- end task item &ndash;&gt;-->
                                <#--</ul>-->
                            <#--</li>-->
                            <#--<li class="footer">-->
                                <#--<a href="#">View all tasks</a>-->
                            <#--</li>-->
                        <#--</ul>-->
                    <#--</li>-->
                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <!-- The user image in the navbar-->
                            <img src="/static/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                            <!-- hidden-xs hides the username on small devices so only the image appears. -->
                            <span class="hidden-xs">Alexander Pierce</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- The user image in the menu -->
                            <li class="user-header">
                                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                <#--<p>-->
                                    <#--Alexander Pierce - Web Developer-->
                                    <#--<small>Member since Nov. 2012</small>-->
                                <#--</p>-->
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <#--<div class="row">-->
                                    <#--<div class="col-xs-4 text-center">-->
                                        <#--<a href="#">Followers</a>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 text-center">-->
                                        <#--<a href="#">Sales</a>-->
                                    <#--</div>-->
                                    <#--<div class="col-xs-4 text-center">-->
                                        <#--<a href="#">Friends</a>-->
                                    <#--</div>-->
                                <#--</div>-->
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <#--<div class="pull-left">-->
                                    <#--<a href="#" class="btn btn-default btn-flat">Profile</a>-->
                                <#--</div>-->
                                <div class="pull-right">
                                    <a href="/j_spring_security_logout" class="btn btn-default btn-flat">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar user panel (optional) -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Alexander Pierce</p>
                    <!-- Status -->
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <#--<li class="header">HEADER</li>-->
                <!-- Optionally, you can add icons to the links -->
                <#--<li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li>-->
                <#--<li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>-->


                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>权限管理

                                                <@sec.authorize access="hasAuthority('aa')">
                                                    有aa的权限，此用户应该是222
                                                </@sec.authorize>

                    </span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/admin/authority/accountList">用户管理</a></li>
                        <li><a href="/admin/authority/authorityList">角色管理</a></li>
                        <li><a href="/admin/authority/resourceList">资源管理</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>商品管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/admin/item/itemList">商品列表

                        </a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>订单管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/admin/order/orderListPG">Admin订单列表</a></li>
                    </ul>
                    <ul class="treeview-menu">
                        <li><a href="/painter/order/orderListPG">画家订单列表</a></li>
                    </ul>
                </li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <#--<section class="content-header">-->
            <#--&lt;#&ndash;<h1>&ndash;&gt;-->
                <#--&lt;#&ndash;Page Header&ndash;&gt;-->
                <#--&lt;#&ndash;<small>Optional description</small>&ndash;&gt;-->
            <#--&lt;#&ndash;</h1>&ndash;&gt;-->
            <#--<ol class="breadcrumb">-->
                <#--<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>-->
                <#--<li class="active">Here</li>-->
            <#--</ol>-->
        <#--</section>-->

        <!-- Main content -->
        <section class="content">
            <#nested>
            <!-- Your Page Content Here -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            Anything you want
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
            <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
            <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
            <!-- Home tab content -->
            <div class="tab-pane active" id="control-sidebar-home-tab">
                <h3 class="control-sidebar-heading">Recent Activity</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript::;">
                            <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                            <div class="menu-info">
                                <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                <p>Will be 23 on April 24th</p>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

                <h3 class="control-sidebar-heading">Tasks Progress</h3>
                <ul class="control-sidebar-menu">
                    <li>
                        <a href="javascript::;">
                            <h4 class="control-sidebar-subheading">
                                Custom Template Design
                <span class="pull-right-container">
                  <span class="label label-danger pull-right">70%</span>
                </span>
                            </h4>

                            <div class="progress progress-xxs">
                                <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                            </div>
                        </a>
                    </li>
                </ul>
                <!-- /.control-sidebar-menu -->

            </div>
            <!-- /.tab-pane -->
            <!-- Stats tab content -->
            <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
            <!-- /.tab-pane -->
            <!-- Settings tab content -->
            <div class="tab-pane" id="control-sidebar-settings-tab">
                <form method="post">
                    <h3 class="control-sidebar-heading">General Settings</h3>

                    <div class="form-group">
                        <label class="control-sidebar-subheading">
                            Report panel usage
                            <input type="checkbox" class="pull-right" checked>
                        </label>

                        <p>
                            Some information about this general settings option
                        </p>
                    </div>
                    <!-- /.form-group -->
                </form>
            </div>
            <!-- /.tab-pane -->
        </div>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->
<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->

<!-- bootstrap datepicker -->
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/static/dist/js/demo.js"></script>
<script src="/static/dist/js/vue.min.js"></script>
<script src="/static/dist/js/vue-router.js"></script>
<script src="/static/dist/js/utils.js"></script>
<script src="/static/dist/js/tools.js"></script>

<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>

</#macro>