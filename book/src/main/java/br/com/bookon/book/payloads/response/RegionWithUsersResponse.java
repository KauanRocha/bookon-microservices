package br.com.bookon.book.payloads.response;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public record RegionWithUsersResponse(List<Long> userIds, double latitude, double longitude, double distance) {
}
