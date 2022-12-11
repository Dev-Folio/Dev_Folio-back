package com.inhatc.dev_folio.project.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class View {
        private Long commentId;
        private String profileImage;
        private String name;
        private String contents;
        private boolean self;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        public void setSelf(boolean self) {
            this.self = self;
        }
    }

    @Getter
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contents {
        private String contents;
    }

}
