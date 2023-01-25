import styled from 'styled-components';
import { MdSearch } from 'react-icons/md';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { TextButton } from '../atoms/Buttons';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 30%;
  height: 40px;
  gap: 10px;
  padding: 0 10px;
  border: 2px solid black;
  box-shadow: var(--boxShadow-00) black;
  background-color: var(--gray-50);
`;

const Input = styled.input`
  border: none;
  width: 90%;
  height: 55%;
  background-color: var(--gray-50);
`;

const SearchButton = styled(TextButton)`
  width: 22px;
`;

const SearchInput = () => {
  const [searchValue, setSearchValue] = useState('');
  const navigate = useNavigate();

  const onChange = e => {
    setSearchValue(e.target.value);
  };

  // 검색어가 아무 것도 없으면 아무런 행동을 취하지 않음
  // 엔터 누르면 해당 검색어로 이동
  const onKeyDown = e => {
    if (e.key === 'Enter') {
      if (searchValue === '') {
        return;
      }
      navigate(`/search?value=${searchValue}`);
    }
  };

  return (
    <Container>
      <Input placeholder="Search" onKeyDown={onKeyDown} onChange={onChange} />
      <SearchButton to={`/search?value=${searchValue}`}>
        <MdSearch size="22" />
      </SearchButton>
    </Container>
  );
};

export default SearchInput;
