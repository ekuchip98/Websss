package com.thunv.controller.api.output;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseOutput <T> {
    private int page;

    private int totalPage;

    private List<T> listResult = new ArrayList<>();
}
