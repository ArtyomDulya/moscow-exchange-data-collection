package models


case class SecurityModel(
                           id: Option[Long],
                           idSecurity: String,
                           secId: String,
                           regNumber: String,
                           name: String,
                           emitentTitle: String
                         )
