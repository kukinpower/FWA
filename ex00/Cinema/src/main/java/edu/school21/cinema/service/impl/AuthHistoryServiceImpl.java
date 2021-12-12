package edu.school21.cinema.service.impl;

import edu.school21.cinema.repository.AuthHistoryRepository;
import edu.school21.cinema.service.AuthHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("authHistoryService")
@RequiredArgsConstructor
public class AuthHistoryServiceImpl implements AuthHistoryService {

  private final AuthHistoryRepository authHistoryRepository;
}
