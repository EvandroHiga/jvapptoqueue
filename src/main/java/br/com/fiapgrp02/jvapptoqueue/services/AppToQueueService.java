package br.com.fiapgrp02.jvapptoqueue.services;

import br.com.fiapgrp02.jvapptoqueue.model.Objeto;
import com.azure.storage.queue.QueueClient;
import com.azure.storage.queue.QueueClientBuilder;
import com.azure.storage.queue.models.QueueStorageException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.*;

@Service
public class AppToQueueService {

    @Value("${env.prd}")
    private String ENV_PRD;

    @Value("${env.hml}")
    private String ENV_HML;

    @Value("${env.msg.ok}")
    private String ENV_MSG_OK;

    @Value("${env.msg.erro}")
    private String ENV_MSG_ERRO;

    public ResponseEntity postMessage(Objeto objeto){
        QueueClient queueClient = new QueueClientBuilder()
                .connectionString(getAzConnStr())
                .queueName(System.getenv("QUEUE_NAME"))
                .buildClient();

        if(isValidEnv(objeto)){
            try{
                queueClient.sendMessage(new Gson().toJson(objeto));
            } catch (QueueStorageException exception){
                return ResponseEntity.status(exception.getStatusCode()).body(exception.getMessage());
            }
            return ResponseEntity.status(CREATED).body(ENV_MSG_OK);
        } else {
            return ResponseEntity.status(BAD_REQUEST).body(ENV_MSG_ERRO);
        }

    }

    private String getAzConnStr(){
        return new StringBuilder()
                .append("DefaultEndpointsProtocol=").append(System.getenv("ENDPOINT_PROTOCOL")).append(";")
                .append("AccountName=").append(System.getenv("ACCOUNT_NAME")).append(";")
                .append("AccountKey=").append(System.getenv("ACCOUNT_KEY")).append(";")
                .append("EndpointSuffix=").append(System.getenv("ENDPOINT_SUFFIX")).toString();
    }

    private boolean isValidEnv(Objeto objeto){
        if(ENV_PRD.equalsIgnoreCase(objeto.getEnv()) ||
                ENV_HML.equalsIgnoreCase(objeto.getEnv())){
            return true;
        } else {
            return false;
        }
    }
}
