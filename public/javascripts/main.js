$(document).ready(function() {
    // Обработчик события для кнопки загрузки ценных бумаг
    $("#loadSecuritiesBtn").click(function() {
        window.location.href = "@routes.SecurityController.securityDataSave().url";
    });

    // Обработчик события для кнопки загрузки истории торгов
    $("#loadTradeHistoryBtn").click(function() {
        window.location.href = "@routes.TradeHistoryController.tradeHistoryDataSave().url";
    });
});