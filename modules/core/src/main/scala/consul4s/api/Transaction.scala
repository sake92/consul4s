package consul4s.api

import consul4s.model.transaction.{TxResults, TxTask}
import sttp.client._

trait Transaction[F[_]] { this: ConsulApi[F] =>
  // PUT	/txn
  def executeTx(txTasks: List[TxTask], dc: Option[String] = None): F[Result[TxResults]] = {
    val requestTemplate = basicRequest.put(uri"$url/txn?dc=$dc")
    val request = requestTemplate.copy(response = jsonDecoder.asTxResults).body(jsonEncoder.txTasksToJson(txTasks))

    val response = sttpBackend.send(request)
    response
  }
}
