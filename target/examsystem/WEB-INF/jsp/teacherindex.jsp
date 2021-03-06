<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        *{
            margin:0;
            padding:0;
        }
        ul,li{
            list-style: none;
        }
        .closed{
            display: block;
            width: 200px;
            background-color: coral;
        }
        #manager ul li{
            width: 200px;
            line-height: 30px;
            text-align: center;
            cursor: pointer;
        }
        .hide{
            display: none;
        }
        .show{
            display: block;
        }
        .open{
            display: block;
            background-color: coral;
        }
        .hide{
            display: none;
        }
        #manager ul li.pro{
            border:1px solid #fff;
        }
        #manager ul li ul li{
            padding:3px 0;
            border: 0;
            color:lightgray;
            line-height: 20px;
            border-top:1px solid #fff;
            background-color: darkorchid;
        }
        #manager ul li ul li:hover{
            color: #fff;
        }
        #manager ul li ul li:last-child{
            border-bottom:1px solid #fff;
        }
    </style>
    <title>TeacherHome</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
    function toggleTable(span){
//            如果span的class为open
        if(span.className=="open"){
//                将span的class设为closed
            span.className="closed";
//                将span的下一个兄弟元素的class改为hide
            span.nextElementSibling.className="hide";
        }else{//否则
//                查找class为tree的元素下的一个class为open的span,保存在open中
            var open=document.querySelector("[class=tree] span[class=open]");
            if(open!=null){
//                    设置open的class为closed
                open.className="closed";
//                    设置open的下一个兄弟元素的class为hide
                open.nextElementSibling.className="hide";
            }
            span.className="open";
            span.nextElementSibling.className="show";
        }
    }
</script>
<body>
<table>
    <tr>
        <td colspan="2" align="right">欢迎${login.account}老师</td>
    </tr>
    <tr valign="top">
        <td width="20%">
            <%@include file="teacherleft.jspf" %>
        </td>
        <td align="center">
            <br>
            <form>

            </form>

        </td>
    </tr>
</table>
</body>
</html>
