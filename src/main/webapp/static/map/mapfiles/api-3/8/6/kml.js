google.maps.__gjsload__('kml', 'var tK={UNKNOWN:0,OK:1,INVALID_REQUEST:2,DOCUMENT_NOT_FOUND:3,FETCH_ERROR:4,INVALID_DOCUMENT:5,DOCUMENT_TOO_LARGE:6,LIMITS_EXCEEDED:7,INTERNAL_ERROR:8,TIMED_OUT:9};function uK(a){this.l=a||[]}var vK;function wK(a){this.l=a||[]}function xK(a){this.l=a||[]}function yK(a){this.l=a||[]}function zK(a){this.l=a||[]}function AK(a){this.l=a||[]}function BK(a){this.l=a||[]}function CK(a){this.l=a||[]}function DK(a){this.l=a||[]}function EK(a){this.l=a||[]}function FK(a){this.l=a||[]}function GK(){this.l=[]}var HK;function IK(a){this.l=a||[]}var JK={Zg:0,Jk:1,Ik:2},KK={rg:0,yl:1,He:2};Ra(uK[D],function(){var a=this.l[0];return a!=j?a:""});\nRa(wK[D],function(){var a=this.l[0];return a!=j?a:""});var LK=new yK;function MK(a){return(a=a.l[4])?new yK(a):LK}var NK=new zK,OK=new wK,PK=new fd,QK=new wK,RK=new BK;function SK(a){return(a=a.l[1])?new BK(a):RK}var TK=new BK;function UK(a){return(a=a.l[2])?new BK(a):TK}var VK=new DK;function WK(a){return(a=a.l[3])?new DK(a):VK}function XK(a){a=a.l[0];return a!=j?a:0}function YK(a){a=a.l[1];return a!=j?a:0}var ZK=new AK;function $K(a){return(a=a.l[0])?new AK(a):ZK}var aL=new AK;\nfunction bL(a){return(a=a.l[1])?new AK(a):aL}function cL(a){a=a.l[0];return a!=j?a:0}var dL=new AK;function eL(a){return(a=a.l[1])?new AK(a):dL}var fL=new CK;function gL(a){return(a=a.l[0])?new CK(a):fL}var hL=new CK;function iL(a){return(a=a.l[1])?new CK(a):hL}var jL=new IK;gj(FK[D],function(){var a=this.l[0];return a!=j?a:-1});var kL=new EK,lL=new fd;FK[D].getDefaultViewport=function(){var a=this.l[4];return a?new fd(a):lL};function mL(a){this.b=a}Ji(mL[D],function(a,b,c){var d=new uK;d.l[0]=a;b&&(d.l[1]=b);vK||(a=[],vK={ba:-1,Y:a},a[1]={type:"s",label:1},a[2]={type:"s",label:1});d=bd(d.l,vK);a=P(this,this.e,c);c=P(this,this.f,c);this.b(d,a,c)});mL[D].f=function(a){a(j)};mL[D].e=function(a,b){a(new FK(b))};function nL(a){this.e=0;this.f=[];this.b=a}Ji(nL[D],function(a,b,c){this.e++;c=Xd(P(this,this.j,c));this.b[qj](a,b,c)});nL[D].j=function(a,b){this.f[q](P(j,a,b));this.e--;if(0==this.e){for(var c=0;c<this.f[B];++c)this.f[c]();this.f=[]}};function oL(){Ug[ic](this)}L(oL,Ug);Ni(oL[D],function(){pL(this)});lp(oL[D],function(){pL(this)});oL[D].projectionBounds_changed=function(){this.P()};oL[D].Z=function(){var a=this.get("projectionBounds"),b=this.get("zoom"),c=this.get("projection");if(a&&c&&N(b)){a=rf(a.H,a.G,a.I,a.K);b=Pk(c,b);c=(a.H+a.I)/2;b=c-Bd(c,0,o[Bb](b.x*b.x+b.y*b.y));a.I-=b;a.H-=b;if(!this.b||!this.b.$a(a))b=a[oj](),a=tq(a),this.b=new qf([new T(b.x-a[u],b.y-a[H]),new T(b.x+a[u],b.y+a[H])]);pL(this)}};\nfunction pL(a){if(a.b){var b;b=a.b[oj]();var c=a.get("projection"),d=a.get("bounds"),e=a.get("zoom");b=!c||!d||!N(e)?ba:al(c,d,e,pf,b);b=rf(od(a.b.H,b.H),od(a.b.G,b.G),pd(a.b.I,b.I),pd(a.b.K,b.K));b[vb](a.e)||(a.set("croppedBounds",b),a.e=b)}};function qL(a,b){return{n:function(b,d){if(d)return j;var e=j,f=b.latLng;a[zb](function(a){if(!e){var b=a.get("bounds");b&&b[uc](f)&&a.get("clickable")!=k&&(e=a)}});return e},j:function(a,d,e){a==Bk?b.set("cursor","pointer"):a==Ak&&b.set("cursor",j);R[s](e,a,new bl(d.latLng,d.b))},zIndex:1}};function rL(a,b){Ug[ic](this);this.e=a;this.b=b}L(rL,Ug);Fa(rL[D],function(a){("bounds"==a||"zoom"==a||"projection"==a)&&this.P()});\nrL[D].Z=function(){var a=this.get("projection"),b=this.get("bounds"),c=this.get("zoom");sL(this);if(a&&b&&N(c)&&!b[Wa]())for(var d=o[w](b.G),b=o[w](b.K),e=this.get("projection"),f=this.get("zoom"),g=this.e,a=(new Q(g.$.f,g.aa.b,i)).lng(),c=(new Q(g.$.b,g.aa.f,i)).lng(),e=Uk(e,g,f),e=o[w](tq(e)[u]),g=g[ob]().lat()-g[ac]().lat(),h=tL(this,d),m=d;d<=b;++d){var f=tL(this,d),p;p=m;var r=d,v=h,z=f,C=(v+z)/2,J=tL(this,(p+r)/2);p=ld((J-C)*((p-r)/(z-v)));r=ld(d-m);if(1<=p&&10<r||d==b)p=new Q(f,c),h=new Q(h,\na),m=new U(e,d-m),h=new fe(p,h),p=g,r=h[ob]().lat(),v=h[ac]().lat(),v=m[H]/(r-v),r=(this.e[ob]().lat()-r)*v,p=ld(p*v),r=new T(0,r),h=h[ob](),this.b.X({e:r,position:h,f:m,scaledSize:new U(m[u],p)}),m=d,h=f}Xd(P(this,this.n))()};function sL(a){a.b[zb](function(a){a.vj=i})}rL[D].n=function(){this.b[zb](function(a){a.vj&&this[tb](a)})};function tL(a,b){var c=a.get("projection"),d=a.get("zoom");return Vk(c,new T(0,b),d).lat()};function uL(a,b,c){Ug[ic](this);this.e=a;this.O=b;this.b=c;this.A={alpha:i,scale:i};R[G](c,Re,P(this,this.xi));R[G](c,Se,P(this,this.Qf))}L(uL,Ug);I=uL[D];Fa(I,function(){this.P()});Ui(I,function(){var a=vL(this);this.b[zb](function(b){lm(b.ec,a,i)})});function vL(a){return vq(a.get("opacity"),1)}\nI.Z=function(){if(sq(this.b)){var a=this.get("projection"),b=this.get("zoom"),c=this.get("center"),d=this.get("offset"),e=this.get("latLngBounds"),f=vL(this);if(N(b)&&e&&a&&d&&c&&(e=al(a,e,b,d,c))&&!e[vb](this.n))this.n=e,this.b[zb](P(this,this.Qf)),this.b[zb](P(this,this.zg,a,b,c,d,f))}};I.xi=function(a){var b=this.get("projection"),c=this.get("zoom"),d=this.get("center"),e=this.get("offset"),f=vL(this);b&&N(c)&&e&&d&&this.zg(b,c,d,e,f,a)};\nI.zg=function(a,b,c,d,e,f){f.b=Tk(a,f[Qj],b,c,d[u],d[H],j);f.ec=Lq(this.e,this.O,f.e,f.f,f.b,f[Fp],this.A);lm(f.ec,e,i);jm(f.ec,1)};I.Qf=function(a){a.ec&&(Wq(a.ec),a.ec=j)};function wL(a,b,c){this.b=P(j,lo,a,b,so+"/maps/api/js/KmlOverlayService.GetFeature",c)}Ji(wL[D],function(a,b){var c=new GK;c.l[0]=a.V;c.l[1]=a.ye;if(a.ma)for(var d in a.ma){var e;e=[];Zc(c.l,2)[q](e);e=new JB(e);e.l[0]=d;e.l[1]=a.ma[d]}HK||(d=[],HK={ba:-1,Y:d},d[1]={type:"s",label:1},d[2]={type:"s",label:1},d[3]={type:"m",label:3,W:OB()});c=bd(c.l,HK);this.b(c,b,b);return c});Hi(wL[D],function(){aa(ja("Not implemented"))});function xL(a,b){if(YK(a)==JK.Zg)return XK(a)*b;if(YK(a)==JK.Ik)return b-XK(a);if(YK(a)==JK.Jk)return XK(a)}function yL(a,b,c){return cL(a)==KK.He?(a=YK(eL(a))==JK.Zg?XK(eL(a))*b:XK(eL(a)),a/c):1};function zL(a,b){this.b=b;this.e=j;R[A](this.b,Re,this,this.jg);R[A](this.b,Se,this,this.kg)}L(zL,W);I=zL[D];I.innerContainer_changed=function(){var a=this.O;this.O=this.get("innerContainer");this.e&&(R[jb](this.e),delete this.e);a&&this.b[zb](P(this,this.kg));this.O&&(this.e=R[A](this.O,ol,this,this.Hi),this.b[zb](P(this,this.jg)))};I.Hi=function(){var a=this;Xd(function(){a.b[zb](P(a,a.Eg))})()};\nI.jg=function(a){if(this.O){var b=Wg(this.O),b=$("div",this.O,new T(b[u],b[H]));em(b);jm(b,2);a.ea=b;b=$("div",a.ea,new T(0,0),j,i);em(b);a.f=b;var b={alpha:i,scale:i,La:P(this,this.yi,a)},c=a.b.l[0];a.fa=Tm((c?new wK(c):QK)[mk](),a.ea,j,j,b)}};I.kg=function(a){a.ea&&Zk(a.ea);a.f&&Zk(a.f);a.fa&&Zk(a.fa);a.ea=j;a.fa=j;a.f=j};I.yi=function(a,b,c){a.ea&&c&&(a.fa=c,em(c),this.Eg(a))};\nI.Eg=function(a){var b=Wg(this.O),c=new U(cL(gL(WK(a.b)))==KK.rg?Wg(a.fa)[u]:cL(gL(WK(a.b)))==KK.He?xL(eL(gL(WK(a.b))),b[u]):Wg(a.fa)[u]*yL(iL(WK(a.b)),b[H],Wg(a.fa)[H]),cL(iL(WK(a.b)))==KK.rg?Wg(a.fa)[H]:cL(iL(WK(a.b)))==KK.He?xL(eL(iL(WK(a.b))),b[H]):Wg(a.fa)[H]*yL(gL(WK(a.b)),b[u],Wg(a.fa)[u])),d=xL($K(UK(a.b)),b[u]),e=xL(bL(UK(a.b)),b[H]),e=b[H]-e-c[H];cm(a.ea,new T(d-xL($K(SK(a.b)),c[u]),e+xL(bL(SK(a.b)),c[H])));Vg(a.ea,c);Vg(a.fa,c);Vg(a.f,c)};function AL(a,b){if(!b.F){var c=P(j,lo,n,cg,so+"/maps/api/js/KmlOverlayService.GetOverlays",bg);b.F=new nL(new mL(c))}a.n||(a.n=yl(function(c){c=P(j,BL,c);b.F[qj](a.get("url"),a.get("token")||j,c)}));a.n(P(j,CL,a,b))}\nfunction CL(a,b,c,d,e,f,g,h,m){var p=a.get("map");if(p&&p==b){var r=IB.Pa(b),p=DL(b);a.j=g;a.e=h;d&&a.set("defaultViewport",d);a.set("metadata",e);a.set("status",f);a.A=m;d=new wL(n,cg,bg);e=Cm(d);f=new fB;f.V=c;for(var v in m)f.ma[v]=m[v];f.b=P(e,e[qj]);jp(f,a.get("clickable")!=k);a.gb=f;r[q](f);a.b||(a.b=R[G](f,Le,P(a,EL,a,b)));for(c=a.j[B]-1;0<=c;--c)p.X(a.j[c]);for(c=0;c<a.e[B];++c)m=a.e[c],m.wc.set("map",b),m.wc[t]("clickable",a),FL(d,m,a,b);c=a.get("preserveViewport");d=a.get("defaultViewport");\n!c&&d&&b.fitBounds(d);a.Ua=new Xf;R[G](a,"clickable_changed",function(){jp(a.gb,a.get("clickable")!=k)})}}function FL(a,b,c,d){var e=new gB(c.gb.V,b.V),c=P(c,GL,c,d,b.wc.get("bounds")[oj](),j),a=P(a,a[qj],e,c);b.Ib=R[G](b.wc,Le,a)}\nfunction GL(a,b,c,d,e){var f={};f.latLng=c;f.pixelOffset=d;if(!e.infoWindowHtml){var g=HL(e),h="";g&&(h=n[wb]("div"),h[Va](g),h=h[up]);e.infoWindowHtml=h}f.featureData=e;R[s](a,Le,f);if(!a.get("suppressInfoWindows")&&a.Ua&&(e=HL(e)))a.Ua.setOptions({pixelOffset:d,position:c,content:e}),a.Ua[rp](b)}function EL(a,b,c,d,e,f){GL(a,b,d,e,f)}\nfunction HL(a){var b=$("div",j,j,j,j,{style:"font-family: Arial, sans-serif; font-size: small"});if(a.info_window_html)Vq(b,a.info_window_html);else if(a[Mb]||a[mq]){if(a[Mb]){var c=$("div",b,j,j,j,{style:"font-weight: bold; font-size: medium; margin-bottom: 0em"});dm(a[Mb],c)}a[mq]&&(c=$("div",b),Vq(c,a[mq]))}else return j;return b}\nfunction BL(a,b){if(!(!b||!a||0!=b[hk]())){for(var c=[],d=[],e={},f=0;f<$c(b.l,5);++f){var g=new xK(Zc(b.l,5)[f]);if(g.l[5]!=j)g=g.l[5],d[q]({b:g?new zK(g):NK});else if(g.l[4]!=j){var h=MK(g).l[1],h=IL(h?new fd(h):PK),m=MK(g).l[0],h=new eg((m?new wK(m):OK)[mk](),h),g=g.l[0];c[q]({wc:h,V:g!=j?g:""})}}g=b.l[1];h=b.l[2];g=(g!=j?g:"")+(h!=j?h:"");h=IL(b.getDefaultViewport());f=b.l[3];if(f=f?new EK(f):kL){var p;p=(m=f.l[3])?new IK(m):jL;var m={},r=p.l[0];Ha(m,r!=j?r:"");r=p.l[2];m.email=r!=j?r:"";p=p.l[1];\nm.uri=p!=j?p:"";p={};r=f.l[0];Ha(p,r!=j?r:"");r=f.l[1];p.description=r!=j?r:"";f=f.l[2];p.snippet=f!=j?f:"";p.author=m;m=p}else m=j;var f=b.l[6],f=f!=j?f:0,v;a:{for(v in gg)if(f==tK[v]){v=gg[v];break a}v="UNKNOWN"}for(f=0;f<$c(b.l,7);++f)p=new JB(Zc(b.l,7)[f]),e[PB(p)]=QB(p);a(g,h,m,v,d,c,e)}}function DL(a){a.j||(a.j=new Ff,(new zL(0,a.j))[t]("innerContainer",a.N()));return a.j}\nfunction JL(a,b){var c=j,d=IB.Pa(b);d[zb](function(b,d){b==a.gb&&(c=d)});c!=j&&d[Ib](c);a.b&&(R[jb](a.b),delete a.b);if(a.j)for(var e=DL(b),d=0;d<a.j[B];++d)e[tb](a.j[d]);if(a.e)for(d=0;d<a.e[B];++d)e=a.e[d],e.wc.set("map",j),R[jb](e.Ib),delete e.Ib;a.Ua&&(a.Ua[$p](),delete a.Ua)}function IL(a){var b=new Q(Lk(Fk(a)),Jk(Fk(a))),a=new Q(Lk(Hk(a)),Jk(Hk(a)));return new fe(a,b)};function KL(){}KL[D].Xk=function(a){var b=a.f,c=a.f=a.get("map");b!=c&&(b&&JL(a,b),c&&AL(a,c))};\nKL[D].zj=function(a,b){if(b&&!b.Q){var c=b.Q=qL(b.e,b.N());rq(b.n,c)}a.b&&(a.b.set("bounds",j),a.e[Aj](),a.b[Aj](),a.j[Aj](),delete a.e,delete a.b,delete a.j);if(b){var c=a.get("bounds"),d=a.get("url"),e=b.N(),f=e.get("panes").overlayLayer,g=new Ff;a.n=g;var h=new oL;h[t]("projectionBounds",e);h[t]("projectionTopLeft",e);h[t]("projection",b);h[t]("zoom",b);h.set("bounds",c);a.e=h;var m=new rL(c,g);m[t]("zoom",b);m[t]("projection",b);m[t]("bounds",h,"croppedBounds");a.b=m;d=new uL(d,f,g);d[t]("offset",\ne);d[t]("zoom",b);d[t]("center",e,"projectionCenterQ");d[t]("projection",b);d[t]("clickable",a);d[t]("opacity",a);d.set("latLngBounds",c);a.j=d;R[E](d,Le,a)}};var LL=new KL;ff.kml=function(a){eval(a)};jf("kml",LL);\n');