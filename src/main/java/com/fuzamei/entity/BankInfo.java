package com.fuzamei.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Created by ylx on 2018/12/22.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "bank",type = "_doc")
public class BankInfo implements Serializable{

    @JsonProperty("id")
    @Field(type = FieldType.Long)
    private Long id;

    @JsonProperty("accountNumber")
    @Field(type = FieldType.Long)
    private Long accountNumber;

    @JsonProperty("balance")
    @Field(type = FieldType.Long)
    private Long balance;

    @JsonProperty("firstname")
    @Field(type = FieldType.Text)
    private String firstname;

    @JsonProperty("lastname")
    @Field(type = FieldType.Text)
    private String lastname;

    @JsonProperty("age")
    @Field(type = FieldType.Long)
    private Long age;

    @JsonProperty("gender")
    @Field(type = FieldType.Text)
    private String gender;

    @JsonProperty("address")
    @Field(type = FieldType.Text)
    private String address;

    @JsonProperty("employer")
    @Field(type = FieldType.Text)
    private String employer;

    @JsonProperty("email")
    @Field(type = FieldType.Text)
    private String email;

    @JsonProperty("city")
    @Field(type = FieldType.Text)
    private String city;

    @JsonProperty("state")
    @Field(type = FieldType.Text)
    private String state;



}
