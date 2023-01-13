import styled from 'styled-components';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 30%;
  height: 40px;
  gap: 10px;
  padding: 0 5px;
  border: 2px solid black;
  box-shadow: var(--boxShadow-00) black;
`;

const Input = styled.input`
  border: none;
  width: 85%;
  height: 55%;
`;

const SearchInput = ({ onChange, asideInput }) => {
  return (
    <Container>
      <Input placeholder="Search" onChange={onChange} />
      {asideInput || ''}
    </Container>
  );
};

export default SearchInput;
