package com.marco.Esportify.model;

import com.marco.Esportify.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamGetResponse {
    private String name;
    private long length;
    private List<User> members;
}
