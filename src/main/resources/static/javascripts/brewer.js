var Brewer = Brewer || {};

Brewer.MaskMoney = (function(){
	
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({decimal :",",thousands:"." });
		this.plain.maskMoney({precision:0 , thousands:"."});
	}
	
	return MaskMoney;
	
}());


$(function(){
	var MaskMoney = new Brewer.MaskMoney();
	MaskMoney.enable();
});

