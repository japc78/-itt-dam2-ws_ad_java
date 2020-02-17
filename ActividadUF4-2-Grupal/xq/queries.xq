(:~ 1 ~:)

(:~ for $recibos in /recibos/recibo
where $recibos/fecha ="2017-09-05"
return $recibos ~:)

(:~ 2 ~:)
(:~ for $detalle in /recibos/recibo//detalle
where $detalle/codigo = "MAR1"
return $detalle ~:)

(:~ 3 ~:)
(:~ for $r in /recibos
let $numdetalles := count($r//detalle)
return $numdetalles ~:)

(:~ 4 ~:)
for $detalles in //detalle
order by $detalles/codigo
return $detalles