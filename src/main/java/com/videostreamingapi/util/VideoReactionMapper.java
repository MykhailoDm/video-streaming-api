package com.videostreamingapi.util;

import com.videostreamingapi.dto.response.VideoReactionResponse;
import com.videostreamingapi.entity.VideoReaction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VideoReactionMapper {
    public static VideoReactionResponse videoReactionToVideoReactionResponse(VideoReaction videoReaction) {
        return new VideoReactionResponse(videoReaction.getId(), videoReaction.isPositive(), videoReaction.getVideo().getId(), videoReaction.getUser().getId());
    }
}
