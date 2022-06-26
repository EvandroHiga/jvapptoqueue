## JvAppToQueue

### Sobre
> Projeto de conclusão da matéria de *Cloud Development* do MBA ***Full Stack Development - Design, Engineering and Deployment*** na [FIAP](https://www.fiap.com.br/).
> Trata-se de um webapp Java que irá receber mensagens em formato JSON, validá-las e inserí-las em uma queue do Azure Storage Account.
> Trabalha em conjunto com o webapp [JvAppFromQueueToDb](https://github.com/EvandroHiga/jvappfromqueuetodb).

### O que é necessário
> JDK 11+, Maven, azure-cli e o Postman.

### Como subir
> 1. Clone este repositório.
> 2. Logue na [Azure](https://portal.azure.com).
> 3. Provisione, caso ainda não tenha feito, uma 'Storage Acccount' com os seguintes dados:
> - Resource Group: **rsg-fiap-40scj-grp02**
> - Storage Account: **fiapgrp02storageaccount**
> - Criar uma Queue com o seguinte nome: **fiapgrp02queue**
> 4. Realize o deploy desta aplicação:
> - Configure a aplicação: **mvn azure-webapp:config**
> - Logue na conta da Azure: **az login**
> - Realize o deploy: **mvn azure-webapp:deploy**
> 5. Após o deploy, pare o aplicação e insira as seguintes variáveis de ambiente:
> - *ACCOUNT_KEY* = *<TOKEN-DO-STORAGE-ACCOUNT>*
> - *ACCOUNT_NAME* = *fiapgrp02storageaccount*
> - *ENDPOINT_PROTOCOL* = *https*
> - *ENDPOINT_SUFFIX* = *core.windows.net*
> - *QUEUE_NAME* = *fiapgrp02queue*
> 6. Inicie a aplicação

### Casos de teste
> - Método: **POST**
> - Endereço: **/**

```
[VALIDO]

{
    "valor01":"carlos",
    "valor02":"higa",
    "env":"prd"
}
```

```
[VALIDO]

{
    "valor01":"carlos",
    "valor02":"higa",
    "env":"hml"
}
```

```
[INVALIDO]

{
    "valor01":"carlos",
    "valor02":"higa",
    "env":"abc"
}
```

