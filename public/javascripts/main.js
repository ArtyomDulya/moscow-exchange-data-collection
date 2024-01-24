$(document).ready(function() {
    $("#loadSecuritiesBtn").click(function() {
        window.location.href = "@routes.SecurityController.securityDataSave().url";
    });

    $("#loadTradeHistoryBtn").click(function() {
        window.location.href = "@routes.TradeHistoryController.tradeHistoryDataSave().url";
    });
});