package com.filesaver.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "pdf_document")
@Data
@Getter
@Setter
public class PdfDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="content_type", nullable=false)
    private String contentType;

    @Lob
    private byte[] data;
}
