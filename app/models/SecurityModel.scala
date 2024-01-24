package models

/**
 * Модель сущности: информация о ценных бумагах
 * @param id
 * @param idSecurity
 * @param secId
 * @param regNumber
 * @param name
 * @param emitentTitle
 */
case class SecurityModel(
                           id: Option[Long],
                           idSecurity: String,
                           secId: String,
                           regNumber: String,
                           name: String,
                           emitentTitle: String
                         )
