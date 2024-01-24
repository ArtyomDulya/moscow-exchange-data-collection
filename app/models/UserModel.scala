package models

/**
 * Модель юзера
 * @param id
 * @param username
 * @param password
 */
case class UserModel(
                      id: Long,
                      username: String,
                      password: String
                    )


