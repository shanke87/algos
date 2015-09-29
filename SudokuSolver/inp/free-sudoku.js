// Copyright 2005 David J. Nixon. All rights reserved.
var c6=new Array("Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"),q6="en-gb",k=0,p5=new Array(81),z1=new Array(5),a,r2,y1;
function Init(){
var i,u=x="",b=e7=w2=0,y=new Date(),z,p,m,j7;y1=document.getElementById("sk5").href;z=Math.ceil((y.getTime()/10000-y.getTimezoneOffset()*6-111758400)/8640);if(z<1)z=1;do{
p=""+y.getFullYear();m=y.getMonth();if(m<9)p+="0";p+=m+1;m=y.getDate();if(m<10)p+="0";p+=m;if(!e7)u+="<tr><td class='sk5' align='right'>"+c6[p.substr(4,2)-1]+"&nbsp;</td>";e7=1;j7=p.substr(6,2)-0;z--;x="<td><a class='sk2' href='#' onMouseover='C("+z+","+p+")' onclick='return H("+p+")'>"+j7+"</a></td>"+x;if((j7==14||j7==27)&&typeof gVert!="undefined"&&gVert>0)x="</tr><tr><td></td>"+x;if(j7<2){
u+=x+"</tr>";x="";e7=0;w2++}
if(b==""){
r2=p;b=z}
y.setDate(y.getDate()-1)}
while(z>0&&w2<3);if(document.getElementById("sk7"))document.getElementById("sk7").innerHTML="<table valign='top' bgcolor='#eeeeee'>"+u+x+"</tr></table>";D();B(k);C(b,r2);x4=new Image();g5="http:\/\/www.";g5+="rankwars.com";g5+="\/shc\/free.gif";x4.src=g5}

function T(g){
if(g-k){
B(g);k=g;C(a,r2)}
return false}

function B(g){
var i;for(i=0;i<5;i++){
if(i==g)document.getElementById("sk"+i).blur();document.getElementById("sk"+i).className=(i-g)?"sk1":"sk0"}
}

function H(p){
window.open("http:\/\/sudokuhints.com\/"+(q6=="en-gb"?"":q6)+"?date="+p, "_blank");return false}

function D(){
var r,c,e=f="",l=30,q=12,h8=12;d2=(document.all)?7:10,m=" 2px solid;",a2=document.compatMode=="CSS1Compat"?7:8;for(r=0;r<9;r++){
for(c=0;c<9;c++){
e+="<div class='sk"+(r<8&&c<8?6:a2)+"' style='position:absolute;left:"+c*l+"px;top:"+r*l+"px;border-top:#"+(r%3?"c0c0c0":"000")+m+(r>7?"border-bottom:#000"+m:"")+"border-left:#"+(c%3?"c0c0c0":"000")+m+(c>7?"border-right:#000"+m:"")+"background-color:#fff;'></div>";f+="<div style='position:absolute;left:"+(h8+c*l)+"px;top:"+(d2+r*l)+"px;border:0;width:"+(l-21)+"px;font:bold "+q+"pt arial,sans-serif;text-align:center;vertical-align:middle"+(navigator.userAgent.toLowerCase().indexOf('safari')<0?";color:#000;background-color:#fff":"")+"' id='"+r+c+"'></div>"}
}
document.getElementById("sk6").innerHTML=e+f;for(r=0;r<9;r++)for(c=0;c<9;c++)p5[r*9+c]=document.getElementById(""+r+c)}

function A(i){
var j=t=f3=0,o=1,k,chr1,m,v,k2,l1,m8,n4="",z4=new Array(0,2,7,6,9,1,5,4,3,8);v=parseInt(i/426);i%=426;if(v){
do{
if(o&1)o=(o>>1)^272;else o=o>>1;if(o<427)if(t++==i){
i=o-1;f3=1}
}
while(o-1&&!f3)}
for(k=0;k<pz[i].length;k++){
l1=0;m8=0;m=pz[i].charAt(k);if(m=="-")m8=1;else{
k2="123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRS".indexOf(m);if(k2>-1){
l1=k2%9+1;m8=parseInt(k2/9)}
else{
k2="TUVWXYZ".indexOf(m);if(k2!=-1)m8=k2+1}
}
while(m8--)n4+="-";if(l1){
for(m=0;m<v;m++)l1=z4[l1];n4+=l1}
if(n4.length==81){
z1[j]=n4;n4="";j++}
}
}

function C(i,p){
var j,m;A(i);a=i;r2=p;for(j=0;j<81;j++){
m=z1[k].substr(j,1);if(m=="-")m=" ";p5[j].innerHTML=m}
document.getElementById("sk5").href=y1+"?date="+p}

