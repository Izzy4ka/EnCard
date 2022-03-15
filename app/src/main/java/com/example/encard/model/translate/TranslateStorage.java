package com.example.encard.model.translate;


import androidx.annotation.NonNull;

import com.example.encard.domain.rapid.TranslateApi;
import com.example.encard.utils.EndPoints;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslateStorage {
    private final TranslateApi translateApi;

    @Inject
    public TranslateStorage(TranslateApi translateApi) {
        this.translateApi = translateApi;
    }

    public void getTranslateGyId(String word, Result result) {
        translateApi.getTranslate(EndPoints.HOST_RAPID
                , EndPoints.KEY_RAPID, EndPoints.EN, word).enqueue(new Callback<Translate>() {
            @Override
            public void onResponse(@NonNull Call<Translate> call, @NonNull Response<Translate> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Translate> call, @NonNull Throwable t) {
                result.onFailure(t);

            }
        });
    }

    public interface Result {
        void onSuccess(Translate translate);

        void onFailure(Throwable throwable);
    }
}
