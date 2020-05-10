package consul4s.api

import consul4s.model.KeyValue
import sttp.client._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric._

trait KVStore[F[_]] {
  // GET /kv/:key
  def get(
    key: String,
    // query parameters
    dc: Option[String] = None,
    recurse: Boolean = false,
    keys: Boolean = false,
    separator: Option[String] = None,
    ns: Option[String] = None
  ): F[Response[Option[KeyValue]]]

  // GET /kv/:key
  def getRaw(
    key: String,
    // query parameters
    dc: Option[String] = None,
    recurse: Boolean = false,
    keys: Boolean = false,
    separator: Option[String] = None,
    ns: Option[String] = None
  ): F[Response[Option[String]]]

  // PUT /kv/:key
  def createOrUpdate(
    key: String,
    value: String,
    // query parameters
    dc: Option[String] = None,
    flags: Option[Int Refined NonNegative] = None,
    cas: Option[Int Refined NonNegative] = None,
    acquire: Option[String] = None,
    release: Option[String] = None,
    ns: Option[String] = None
  ): F[Response[Boolean]]

  // DELETE /kv/:key
  def delete(
    key: String,
    recurse: Boolean = false,
    cas: Option[Int Refined NonNegative] = None,
    ns: Option[String] = None
  ): F[Response[Boolean]]
}
