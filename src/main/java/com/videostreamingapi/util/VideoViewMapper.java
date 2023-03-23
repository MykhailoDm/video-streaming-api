package com.videostreamingapi.util;

import com.videostreamingapi.dto.response.VideoViewResponse;
import com.videostreamingapi.entity.VideoView;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VideoViewMapper {

    public static VideoViewResponse videoViewToVideoViewResponse(VideoView videoView) {
        return new VideoViewResponse(videoView.getId(), videoView.getVideo().getId(),
                videoView.getUser().getId(), videoView.getCreatedDate());
    }
}
