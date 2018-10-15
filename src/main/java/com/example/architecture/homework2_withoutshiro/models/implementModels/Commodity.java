package com.example.architecture.homework2_withoutshiro.models.implementModels;

import com.example.architecture.homework2_withoutshiro.constants.DataBaseConst;
import com.example.architecture.homework2_withoutshiro.models.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = DataBaseConst.Commodity_DATABASE_NAME)
public class Commodity extends BaseModel {

}
