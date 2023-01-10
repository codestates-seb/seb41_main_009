import styled from 'styled-components';
import Menu from '../molecules/SidebarMenu';

const Container = styled.div`
  position: fixed;
  left: 0;
  top: 100px;
  z-index: 1;
  height: 100%;
  display: flex;
  justify-content: flex-start;
  border: 1px solid #333;
  flex-direction: column;
  background-color: salmon;
`;

const Sidebar = () => {
  return (
    <Container>
      <Menu />
      <Menu />
      <Menu />
      <Menu />
      <Menu />
    </Container>
  );
};

export default Sidebar;
