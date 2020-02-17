`sm_sortorderdetailhistory 
    id ,
    sortorderid 订单号（对应表sm_sortorderhistory的id字段）,
    sortbatchcode 分拣批次号（对应表sm_sortorderhistory的sortbatchcode字段）,
    channelcode
    channelname 
    channelgroup 
    sortaddress
    productcode 产品代码,
    productname 产品名称,
    productno
    piecebarcode
    barbarcode
    abnormity 是否为异型烟（1为已异型烟，0为普通烟）,
    sortquantity 产品数量,
    barcode
    createtime
    updatetime
    rowversion 

sm_sortorderhistory (
       id 订单号,
       sortbatchcode 分拣批次号,
       packno
       exportpackorder
       exportpackcount 
       deliveryroutepackorder
       deliveryroutepackcount 
       customerpackorder
       customerpackcount 
       orderid 
       orderdate 订单日期
       ordertype 
       customercode 客户编号
       customername 客户名称
       customeraddress 
       customerphone 
       customerinfo 
       customerorder 
       nationcode 
       deliveryroutecode 配送路径编号
       deliveryroutename 配送路径名称
       deliveryorder 
       deliverydate 
       packquantity 
       customerquantity 
       productcount 
       exportno
       sortstarttime 
       sortfinishtime 
       companycode 
       companyname 
       sortstatus 
       createtime 
       updatetime 
       rowversion `